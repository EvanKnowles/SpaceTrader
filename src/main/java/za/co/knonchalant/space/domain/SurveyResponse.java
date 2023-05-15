package za.co.knonchalant.space.domain;

import java.util.List;

public class SurveyResponse {
    private Cooldown cooldown;
    private List<Survey> surveys;

    public Cooldown getCooldown() {
        return cooldown;
    }

    public void setCooldown(Cooldown cooldown) {
        this.cooldown = cooldown;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }
}
