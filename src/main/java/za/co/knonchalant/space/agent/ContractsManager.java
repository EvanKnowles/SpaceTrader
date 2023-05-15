package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.Spacer;
import za.co.knonchalant.space.domain.Contract;

import java.io.IOException;
import java.util.List;

public class ContractsManager {
    private static List<Contract> contracts;

    public static List<Contract> getContracts() {
        return contracts;
    }

    public static void setContracts(List<Contract> contracts) {
        ContractsManager.contracts = contracts;
    }

    public static void refresh(Spacer spacer) throws IOException {
        contracts = spacer.contracts();
    }

    public static void update(Contract contract) {
        int i = contracts.indexOf(contract);
        contracts.set(i, contract);
    }

    public static boolean hasContracts() {
        return !contracts.isEmpty();
    }
}
