package za.co.knonchalant.space.domain;

public class AcceptResponse {
    private Agent agent;
    private Contract contract;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
