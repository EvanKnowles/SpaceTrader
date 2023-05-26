package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.RestAPI;
import za.co.knonchalant.space.Spacer;
import za.co.knonchalant.space.agent.domain.Settings;
import za.co.knonchalant.space.domain.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.System;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    static String settingsLocationB = "C:\\dev\\projects\\SpaceTrader\\settings.json";

    public static void mainR(String[] args) throws IOException {
        Spacer register = Spacer.register("Viat", "QUANTUM");

        Settings settings = new Settings();
        settings.setToken(register.getToken());
        settings.persist(settingsLocationB);
    }

    public static void main(String[] args) throws IOException {
        String settingsLocation = args[0];
        boolean notSecure = args[1] != null;

        Settings settings = Settings.get(settingsLocation);
        String token = settings.getToken();
        Spacer api = Spacer.token(token);
        System.out.println(api.getAgentDetails());
        System.out.println();

        new Thread(new RestAPI(api, notSecure)).start();

        ContractsManager.refresh(api);
        List<Contract> contracts = ContractsManager.getContracts();
        // just accepting all the contracts for now
        for (Contract contract : contracts) {
            if (!contract.getAccepted()) {
                api.accept(contract);
            }
        }

        System.out.println(contracts);
        ShipManager.setShips(api.ships());

        Map<String, IRole> shipRoles = new HashMap<>();
        while (true) {
            try {
                List<Ship> ships = ShipManager.getShips();
                for (int i = 0; i < ships.size(); i++) {
                    Ship ship = ships.get(i);
                    if (!shipRoles.containsKey(ship.getSymbol())) {
                        if (EShipRole.EXCAVATOR.is(ship)) {
                            shipRoles.put(ship.getSymbol(), new Miner());
                        } else {
                            shipRoles.put(ship.getSymbol(), new Purchaser());
                        }
                    }

                    IRole iRole = shipRoles.get(ship.getSymbol());
                    if (iRole.getResumeAfter() == null || iRole.getResumeAfter().before(new Date())
                    || ship.getCooldown() == null || ship.getCooldown().getExpiration().before(new Date())) {
                        ship.setCooldown(null);
                        iRole.perform(api, ship);
                    }
                }
            } catch (Exception restServerException) {
                System.out.println("Server sad");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                restServerException.printStackTrace(pw);

                ExceptionManager.addError(sw.toString());
                restServerException.printStackTrace();
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
