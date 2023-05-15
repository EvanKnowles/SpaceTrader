package za.co.knonchalant.space;

import za.co.knonchalant.space.client.exception.RestClientException;
import za.co.knonchalant.space.domain.*;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    static String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZGVudGlmaWVyIjoiVklBVCIsImlhdCI6MTY4MzU0NjQ4MSwic3ViIjoiYWdlbnQtdG9rZW4ifQ.Chk-HzV2QYEhYP9Rd6WXAY5GXKc9-M6G70Q0ltIm7owROMMp2mbC391na-Bc0OUgheez3NwgHGkXUAZW294r0SoJ-w1ibJ6hal2uV6pyhbDBNLBHlNuOcDwsRbYLbImdkh04PU0sdtRr3KgJHr36Dp9Aj0K1zkU3G5WnR6EAFvDEbXNDDnzdP6vC4LeRg3aiPbMZGP5ERfFBK1_X3GH_Rsy_j4ndK_RnVfE5yqtuakS7FwoFbYwyzKvrYZvF6YPbMYASF4SJimS5tVBdC3IHrpDApnJuis7NRg-UeYlZ3Z57WTj0EQWd1D87GViH9IMndAtSTYYdv2kfVvZEL4gHHmajHyojg7K7FxsgSNtuLOevXM1ML4cIbRn7tvA9ljVFtodTA-6Pomb8v0JKYGHLKs9zBemy_kiT0Wq5BEm6_ri26vjKLkF3FZvYZkvC64v-qcWzHBt2jXvehbJt-hLNPmKOOAgXm15_RjmXP3kKYmaMl7zEUH7qw-5ydIMd_NQm";

    public static void main(String[] args) throws IOException {
        Spacer spacer = Spacer.token(token);
        System.out.println(spacer.getAgentDetails());
        System.out.println();
        Contract contract = spacer.contracts().get(0);
        ETradeSymbol target = null;
        long amount = -1;
        for (Deliver deliver : contract.getTerms().getDeliver()) {
            System.out.println(deliver);
            target = ETradeSymbol.valueOf(deliver.getTradeSymbol());
            amount = deliver.getUnitsRequired();
        }

        for (Ship ship : spacer.ships()) {
            if (EShipRole.EXCAVATOR.is(ship)) {
                if (ship.getCargo().getCapacity() - ship.getCargo().getUnits() == 0) {
                    System.out.println("Full, going to sell");
                    spacer.dock(ship);
                    for (Inventory inventory : ship.getCargo().getInventory()) {
                        if (!target.is(inventory)) {
                            System.out.println("Selling: " + inventory);
                            SellResponse sell = spacer.sell(ship, inventory);

                        }
                    }

                }
                Inventory inventory = ship.getCargo().find(target);
                System.out.println(ship.getNav());
                while (amount > 0) {
                    if (EShipNavStatus.IN_ORBIT.is(ship)) {
                        try {
                            ExtractResponse extract = spacer.extract(ship);
                            Yield yield = extract.getExtraction().getYield();
                            System.out.println(yield.getSymbol() + " " + yield.getUnits() + ", sleeping for " + extract.getCooldown().getRemainingSeconds());
                            if (target.is(yield.getSymbol())) {
                                amount -= yield.getUnits();
                                System.out.println("Found some, need " + amount);
                            }
                            spacer.cargo(ship);
                            Thread.sleep(extract.getCooldown().getRemainingSeconds() * 1000);
                        } catch (RestClientException ex) {
                            System.out.println("Cooling down: " + ex.getMessage());
                            try {
                                Thread.sleep(20_000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    } else if (EShipNavStatus.DOCKED.is(ship)) {
                        Nav orbit = spacer.orbit(ship);
                        System.out.println(orbit);
                    }
                }
            }
        }


    }
}
