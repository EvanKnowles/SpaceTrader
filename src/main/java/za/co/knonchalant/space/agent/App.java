package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.RestAPI;
import za.co.knonchalant.space.Spacer;
import za.co.knonchalant.space.agent.domain.Settings;
import za.co.knonchalant.space.client.exception.RestClientException;
import za.co.knonchalant.space.client.exception.RestServerException;
import za.co.knonchalant.space.domain.*;

import java.io.IOException;
import java.lang.System;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    static String settingsLocation = "C:\\dev\\projects\\SpaceTrader\\settings.json";

    public static void mainRegister(String[] args) throws IOException {
        Spacer register = Spacer.register("Viat", "COSMIC");

        Settings settings = new Settings();
        settings.setToken(register.getToken());
        settings.persist(settingsLocation);
    }

    public static void main(String[] args) throws IOException {
        Settings settings = Settings.get(settingsLocation);
        String token = settings.getToken();
        Spacer spacer = Spacer.token(token);
        System.out.println(spacer.getAgentDetails());
        System.out.println();

        new Thread(new RestAPI(spacer)).start();

        ContractsManager.refresh(spacer);
        ShipManager.setShips(spacer.ships());

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
                    if (iRole.getResumeAfter() == null || iRole.getResumeAfter().before(new Date())) {
                        iRole.perform(spacer, ship);
                    }
                }
            } catch (RestServerException restServerException) {
                System.out.println("Server sad");
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
