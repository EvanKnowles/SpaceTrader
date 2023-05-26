package za.co.knonchalant.space;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import za.co.knonchalant.space.agent.ContractsManager;
import za.co.knonchalant.space.agent.TransactionManager;
import za.co.knonchalant.space.client.REST;
import za.co.knonchalant.space.client.exception.RestClientException;
import za.co.knonchalant.space.domain.*;
import za.co.knonchalant.space.domain.System;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spacer {
    private String token;
    private Agent agentDetails;

    private static Map<String, Waypoint> waypointMap = new HashMap<>();
    private static Map<String, List<Waypoint>> waypointsMap = new HashMap<>();
    Path waypointCachePath = Path.of("C:\\dev\\projects\\SpaceTrader\\waypoint.json");
    Path waypointsCachePath = Path.of("C:\\dev\\projects\\SpaceTrader\\waypoints.json");

    public Spacer(String token) {
        this.token = token;
        if (Files.exists(waypointCachePath)) {
            try {
                waypointMap = read(waypointCachePath, new TypeToken<>() {
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (Files.exists(waypointsCachePath)) {
            try {
                waypointsMap = read(waypointsCachePath, new TypeToken<>() {
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getToken() {
        return token;
    }

    private <T> T read(Path cachePath, TypeToken<T> typeOfT) throws IOException {
        return new Gson().fromJson(Files.readString(cachePath), typeOfT);
    }

    public static Spacer token(String token) throws IOException {
        DataResponse<Agent> agent = REST.url("https://api.spacetraders.io/v2/my/agent")
                .authorization(token)
                .get(new TypeToken<>() {
                });

        Spacer spacer = new Spacer(token);
        spacer.updateAgentDetails(agent.getData());
        return spacer;
    }

    public static Spacer register(String symbol, String faction) throws IOException {
        RegisterRequest registerRequest = new RegisterRequest(symbol, faction);
        Response response = REST.url("https://api.spacetraders.io/v2/register")
                .body(registerRequest)
                .post(Response.class);
        Spacer spacer = new Spacer(response.getData().getToken());
        spacer.updateAgentDetails(response.getData().getAgent());
        return spacer;
    }

    public Agent agent() throws IOException {
        DataResponse<Agent> agent = REST.url("https://api.spacetraders.io/v2/my/agent")
                .authorization(token)
                .get(new TypeToken<>() {
                });
        return agent.getData();
    }

    public NavigationResponse navigate(Ship ship, Waypoint waypoint) throws IOException {
        return navigate(ship, waypoint.getSymbol());
    }

    public SurveyResponse survey(String shipSymbol) throws IOException {
        DataResponse<SurveyResponse> response = rest("https://api.spacetraders.io/v2/my/ships/%s/survey", shipSymbol)
                .post(new TypeToken<>() {
                });
        return response.getData();
    }

    public NavigationResponse navigate(Ship ship, String waypointSymbol) throws IOException {
        DataResponse<NavigationResponse> response = rest("https://api.spacetraders.io/v2/my/ships/%s/navigate", ship.getSymbol())
                .body(new WaypointRequest(waypointSymbol))
                .post(new TypeToken<>() {
                });
        ship.setDisplayMessage("Navigating to " + waypointSymbol);
        ship.setNav(response.getData().getNav());
        ship.setFuel(response.getData().getFuel());
        return response.getData();
    }

    public MarketResponse purchase(Ship ship, String symbol, long units) throws IOException {
        ship.setDisplayMessage("Buying " + units + " " + symbol);
        long before = agentDetails.getCredits();
        DataResponse<MarketResponse> response = rest("https://api.spacetraders.io/v2/my/ships/%s/purchase", ship.getSymbol())
                .body(new MarketPurchaseRequest(symbol, units))
                .post(new TypeToken<>() {
                });

        ship.setCargo(response.getData().getCargo());
        updateAgentDetails(response.getData().getAgent());
        TransactionManager.add("Bought " + units + " " + symbol, agentDetails.getCredits() - before);

        return response.getData();
    }

    public Market market(Nav nav) throws IOException {
        return market(nav.getSystemSymbol(), nav.getWaypointSymbol());
    }

    public Market market(String systemSymbol, String waypointSymbol) throws IOException {
        DataResponse<Market> response = rest("https://api.spacetraders.io/v2/systems/%s/waypoints/%s/market", systemSymbol, waypointSymbol)
                .get(new TypeToken<>() {
                });
        return response.getData();
    }


    public Nav dock(Ship ship) throws IOException {
        DataResponse<NavigationResponse> response = rest("https://api.spacetraders.io/v2/my/ships/%s/dock", ship.getSymbol())
                .post(new TypeToken<>() {
                });
        ship.setNav(response.getData().getNav());
        return response.getData().getNav();
    }

    public SellResponse sell(Ship ship, Inventory inventory) throws IOException {
        long before = agentDetails.getCredits();
        DataResponse<SellResponse> response = rest("https://api.spacetraders.io/v2/my/ships/%s/sell", ship.getSymbol())
                .body(inventory)
                .post(new TypeToken<>() {
                });
        ship.setCargo(response.getData().getCargo());
        updateAgentDetails(response.getData().getAgent());
        TransactionManager.add("Sold " + inventory, agentDetails.getCredits() - before);
        return response.getData();
    }

    public RefuelResponse refuel(Ship ship) throws IOException {
        long before = agentDetails.getCredits();
        try {
            DataResponse<RefuelResponse> response = rest("https://api.spacetraders.io/v2/my/ships/%s/refuel", ship.getSymbol())
                    .post(new TypeToken<>() {
                    });
            updateAgentDetails(response.getData().getAgent());
            TransactionManager.add("Refuelled " + ship.getSymbol(), agentDetails.getCredits() - before);
            ship.setFuel(response.getData().getFuel());
            return response.getData();
        } catch (RestClientException ex) {
            ship.setDisplayMessage("Not enough money for fuel");
            return null;
        }
    }

    private void updateAgentDetails(Agent agent) {
        if (agentDetails != null) {
            long previousCredits = agentDetails.getCredits();
            long result = previousCredits - agent.getCredits();
            if (Math.abs(result) > 0) {
                java.lang.System.out.println("Credit change: " + result);
            }
        }
        this.agentDetails = agent;
    }

    public Cargo cargo(Ship ship) throws IOException {
        DataResponse<Cargo> response = rest("https://api.spacetraders.io/v2/my/ships/%s/cargo", ship.getSymbol())
                .get(new TypeToken<>() {
                });
        ship.setCargo(response.getData());
        return response.getData();
    }

    public ExtractResponse extract(Ship ship) throws IOException {
        return extract(ship, null);
    }

    public ExtractResponse extract(Ship ship, Survey survey) throws IOException {
        REST rest = rest("https://api.spacetraders.io/v2/my/ships/%s/extract", ship.getSymbol());
        if (survey != null) {
            rest.body(survey);
        }
        DataResponse<ExtractResponse> response = rest
                .post(new TypeToken<>() {
                });
        ship.setCargo(response.getData().getCargo());
        return response.getData();
    }

    public Nav orbit(Ship ship) throws IOException {
        DataResponse<NavigationResponse> response = rest("https://api.spacetraders.io/v2/my/ships/%s/orbit", ship.getSymbol())
                .post(new TypeToken<>() {
                });
        ship.setNav(response.getData().getNav());
        return response.getData().getNav();
    }

    public List<Ship> ships() throws IOException {
        DataResponse<List<Ship>> shipResponse = url("https://api.spacetraders.io/v2/my/ships")
                .get(new TypeToken<>() {
                });
        Meta meta = shipResponse.getMeta();
        List<Ship> result = shipResponse.getData();


        int i = 2;
        while (result.size() < meta.getTotal()) {
            shipResponse = rest("https://api.spacetraders.io/v2/my/ships?page=%s", String.valueOf(i))
                    .get(new TypeToken<>() {
                    });
            result.addAll(shipResponse.getData());
            i++;
        }

        return result;
    }

    private REST url(String url) {
        return REST.url(url)
                .authorization(token);
    }

    public Waypoint waypoint(String systemSymbol, String wayPointSymbol) throws IOException {
        if (waypointMap.containsKey(wayPointSymbol)) {
            return waypointMap.get(wayPointSymbol);
        }
        Waypoint waypoint = url(String.format("https://api.spacetraders.io/v2/systems/%s/waypoints/%s", systemSymbol, wayPointSymbol))
                .get(WaypointResponse.class).getData();
        waypointMap.put(wayPointSymbol, waypoint);
        Files.writeString(waypointCachePath, new Gson().toJson(waypointMap));
        return waypoint;
    }

    public Waypoint waypoint(Nav nav) throws IOException {
        return waypoint(nav.getSystemSymbol(), nav.getWaypointSymbol());
    }

    public Shipyard shipyard(Waypoint waypoint) throws IOException {
        return url(String.format("https://api.spacetraders.io/v2/systems/%s/waypoints/%s/shipyard", waypoint.getSystemSymbol(), waypoint.getSymbol()))
                .get(ShipyardResponse.class).getData();
    }

    public List<Waypoint> waypoints(String systemSymbol) throws IOException {
        if (waypointsMap.containsKey(systemSymbol)) {
            return waypointsMap.get(systemSymbol);
        }

        List<Waypoint> waypoints = url(String.format("https://api.spacetraders.io/v2/systems/%s/waypoints", systemSymbol))
                .get(WaypointsResponse.class).getData();
        waypointsMap.put(systemSymbol, waypoints);
        Files.writeString(waypointsCachePath, new Gson().toJson(waypointsMap));

        return waypoints;
    }

    public System system(String systemSymbol) throws IOException {
        List<System> systems = read(Path.of("C:\\dev\\projects\\SpaceTrader\\systems.json"), new TypeToken<>() {
        });
        for (System system : systems) {
            if (system.getSymbol().equals(systemSymbol)) {
                return system;
            }
        }

        return null;
    }

    public List<Waypoint> waypoints(Nav nav) throws IOException {
        return waypoints(nav.getSystemSymbol());
    }

    public List<Contract> contracts() throws IOException {
        return url("https://api.spacetraders.io/v2/my/contracts")
                .get(ContractsResponse.class).getData();
    }

    public Contract contract(String id) throws IOException {
        return rest("https://api.spacetraders.io/v2/my/contracts/%s", id)
                .get(ContractResponse.class).getData();
    }

    public DeliverResponse deliver(String id, Ship ship, String tradeSymbol, long units) throws IOException {
        DeliverContract deliverContract = new DeliverContract(tradeSymbol, ship.getSymbol(), units);
        DataResponse<DeliverResponse> data = rest("https://api.spacetraders.io/v2/my/contracts/%s/deliver", id)
                .body(deliverContract)
                .post(new TypeToken<>() {
                });
        ship.setCargo(data.getData().getCargo());
        ContractsManager.update(data.getData().getContract());
        return data.getData();
    }

    private REST rest(String url, String... id) {
        return REST.url(url, id)
                .authorization(token);
    }

    public Contract accept(Contract contract) throws IOException {
        DataResponse<AcceptResponse> post = rest("https://api.spacetraders.io/v2/my/contracts/%s/accept", contract.getId())
                .post(new TypeToken<>() {
                });
        agentDetails = post.getData().getAgent();
        return post.getData().getContract();
    }

    public Ship purchaseShip(EShipType shipType, Shipyard shipyard) throws IOException {
        return purchaseShip(shipType.name(), shipyard.getSymbol());
    }

    public Ship ship(String symbol) throws IOException {
        DataResponse<Ship> post = rest("https://api.spacetraders.io/v2/my/ships/%s", symbol)
                .get(new TypeToken<>() {
                });

        return post.getData();
    }

    public Ship purchaseShip(String shipType, String waypointSymbol) throws IOException {
        long before = agentDetails.getCredits();
        PurchaseRequest purchaseRequest = new PurchaseRequest(shipType, waypointSymbol);
        DataResponse<PurchaseResponse> post = url("https://api.spacetraders.io/v2/my/ships").body(purchaseRequest)
                .post(new TypeToken<>() {
                });

        updateAgentDetails(post.getData().getAgent());
        TransactionManager.add("Bought ship: " + shipType, agentDetails.getCredits() - before);
        return post.getData().getShip();
    }

    public Agent getAgentDetails() {
        return agentDetails;
    }
}
