package za.co.knonchalant.space.agent.domain;

import java.util.List;

public class AllTransactions {
    private long finalCredits;
    private List<Transaction> transactionList;

    public AllTransactions(long finalCredits, List<Transaction> transactionList) {
        this.finalCredits = finalCredits;
        this.transactionList = transactionList;
    }

    public long getFinalCredits() {
        return finalCredits;
    }

    public void setFinalCredits(long finalCredits) {
        this.finalCredits = finalCredits;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
