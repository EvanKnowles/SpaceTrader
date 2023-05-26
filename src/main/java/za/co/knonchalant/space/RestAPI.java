package za.co.knonchalant.space;

import com.google.gson.Gson;
import za.co.knonchalant.space.agent.*;
import za.co.knonchalant.space.agent.domain.AllTransactions;

import static spark.Spark.*;

public class RestAPI implements Runnable {
    private final Spacer spacer;

    public RestAPI(Spacer spacer) {
        this.spacer = spacer;
    }

    @Override
    public void run() {
        port(4567);

        secure("/etc/letsencrypt/live/knowles.co.za/knowles.jks", "password", null, null);

        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Headers", "*");
            res.type("application/json");
        });

        get("/error", (request, response) -> new Gson().toJson(ExceptionManager.getErrors()));
        get("/agent", (request, response) -> new Gson().toJson(spacer.getAgentDetails()));
        get("/contracts", (request, response) -> new Gson().toJson(ContractsManager.getContracts()));
        get("/surveys", (request, response) -> new Gson().toJson(SurveyManager.getNeedSurvey()));
        get("/ships", (request, response) -> new Gson().toJson(ShipManager.getShips()));
        get("/transactions", (request, response) -> new Gson().toJson(TransactionManager.get()));
        get("/all-transactions", (request, response) -> new Gson().toJson(new AllTransactions(spacer.getAgentDetails().getCredits(), TransactionManager.getAll())));
    }
}
