package za.co.knonchalant.space;

import com.google.gson.Gson;
import za.co.knonchalant.space.agent.ContractsManager;
import za.co.knonchalant.space.agent.ShipManager;
import za.co.knonchalant.space.agent.SurveyManager;

import static spark.Spark.*;

public class RestAPI implements Runnable {
    private final Spacer spacer;

    public RestAPI(Spacer spacer) {
        this.spacer = spacer;
    }

    @Override
    public void run() {
        port(4567);

        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Headers", "*");
            res.type("application/json");
        });

        get("/agent", (request, response) -> new Gson().toJson(spacer.getAgentDetails()));
        get("/contracts", (request, response) -> new Gson().toJson(ContractsManager.getContracts()));
        get("/surveys", (request, response) -> new Gson().toJson(SurveyManager.getNeedSurvey()));
        get("/ships", (request, response) -> new Gson().toJson(ShipManager.getShips()));
    }
}
