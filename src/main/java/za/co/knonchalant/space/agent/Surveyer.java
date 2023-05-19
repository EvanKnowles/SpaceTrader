package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.Spacer;
import za.co.knonchalant.space.agent.domain.WantedSurvey;
import za.co.knonchalant.space.client.exception.RestClientException;
import za.co.knonchalant.space.domain.*;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class Surveyer implements IRole {
    private String target;
    private Date resumeAfter;

    @Override
    public void perform(Spacer spacer, Ship ship) throws IOException {
        if (ship.inTransit()) {
            return;
        }

        if (target == null) {
            WantedSurvey survey = SurveyManager.getSurvey();
            if (survey != null) {
                target = survey.getWaypointSymbol();
            }
        }

        if (target == null) {
            ship.setDisplayMessage("Not surveying, no targets");
            Miner miner = new Miner();
            miner.perform(spacer, ship);
            resumeAfter = miner.getResumeAfter();
            return;
        }

        if (!Objects.equals(target, ship.getNav().getWaypointSymbol())) {
            ship.setDisplayMessage("Docking to refuel");
            spacer.dock(ship);
            ship.setDisplayMessage("Refuelling...");
            spacer.refuel(ship);
            ship.setDisplayMessage("Heading to orbit...");
            spacer.orbit(ship);
            log(ship, "Going to survey: " + target);
            spacer.navigate(ship, target);
            return;
        }

        try {
            log(ship, "Surveying " + target);
            SurveyResponse surveyResponse = spacer.survey(ship.getSymbol());
            ship.setCooldown(surveyResponse.getCooldown());

            SurveyManager.addAll(surveyResponse.getSurveys());
            resumeAfter = cooldown(surveyResponse.getCooldown().getRemainingSeconds());
        } catch (RestClientException ex) {
            ex.printStackTrace();
            ErrorResponse from = ErrorResponse.from(ex.getMessage());
            ship.setDisplayMessage("Error during survey");
            ship.setCooldown(from.getError().getData().getCooldown());
            resumeAfter = from.getError().getData().getCooldown().getExpiration();
        }
    }

    @Override
    public Date getResumeAfter() {
        return resumeAfter;
    }
}
