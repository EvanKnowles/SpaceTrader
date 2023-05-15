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
            return;
        }

        if (!Objects.equals(target, ship.getNav().getWaypointSymbol())) {
            log(ship, "Going to survey: " + target);
            spacer.navigate(ship, target);
            return;
        }

        try {
            SurveyResponse surveyResponse = spacer.survey(ship.getSymbol());
            SurveyManager.addAll(surveyResponse.getSurveys());
            resumeAfter = cooldown(surveyResponse.getCooldown().getRemainingSeconds());
        } catch (RestClientException ex) {
            ErrorResponse from = ErrorResponse.from(ex.getMessage());
            resumeAfter = from.getError().getData().getCooldown().getExpiration();
        }

    }

    @Override
    public Date getResumeAfter() {
        return resumeAfter;
    }
}
