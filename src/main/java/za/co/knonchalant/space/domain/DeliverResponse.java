package za.co.knonchalant.space.domain;

public class DeliverResponse {
    private Contract contract;
    private Cargo cargo;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
