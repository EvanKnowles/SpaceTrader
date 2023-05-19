package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.agent.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private static final List<Transaction> transactionList = new ArrayList<>();

    public static void add(String description, long credits) {
        if (credits == 0) {
            return;
        }
        transactionList.add(new Transaction(description, credits, transactionList.size() + 1));
    }

    public static List<Transaction> getAll() {
        return transactionList;
    }

    public static List<Transaction> get() {
        if (transactionList.isEmpty()) {
            return transactionList;
        }
        return transactionList.subList(Math.max(transactionList.size() - 11, 0), transactionList.size() - 1);
    }


}
