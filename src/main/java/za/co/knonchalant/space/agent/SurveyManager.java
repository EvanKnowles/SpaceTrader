package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.agent.domain.WantedSurvey;
import za.co.knonchalant.space.domain.ETradeSymbol;
import za.co.knonchalant.space.domain.Survey;

import java.util.*;

public class SurveyManager {
    private static Map<String, List<Survey>> surveyMap = new HashMap<>();
    private static Set<WantedSurvey> needSurvey = new HashSet<>();

    public static Survey get(String waypointSymbol) {
        List<Survey> surveys = surveyMap.get(waypointSymbol);

        if (surveys == null || surveys.isEmpty()) {
            return null;
        }
        return surveys.get(new Random().nextInt(surveys.size()));
    }
    public static List<Survey> get(String waypointSymbol, ETradeSymbol tradeSymbol) {
        List<Survey> surveys = surveyMap.get(waypointSymbol);

        if (surveys == null || surveys.isEmpty()) {
            needSurvey.add(new WantedSurvey(waypointSymbol, tradeSymbol));
            return Collections.emptyList();
        }

        for (int i = 0; i < surveys.size();) {
            Survey survey = surveys.get(i);
            if (survey.getExpiration().before(new Date())) {
                surveys.remove(i);
                return Collections.emptyList();
            } else {
                i++;
            }
        }

        ArrayList<Survey> matchingSurveys = new ArrayList<>();
        for (Survey survey : surveys) {
            if (survey.contains(tradeSymbol)) {
                matchingSurveys.add(survey);
            }
        }

        if (matchingSurveys.isEmpty()) {
            needSurvey.add(new WantedSurvey(waypointSymbol, tradeSymbol));
            return Collections.emptyList();
        }

        return matchingSurveys;
    }

    public static synchronized WantedSurvey getSurvey() {
        if (needSurvey.isEmpty()) {
            return null;
        }

        WantedSurvey next = needSurvey.iterator().next();
        needSurvey.remove(next);
        return next;
    }

    public static Set<WantedSurvey> getNeedSurvey() {
        return needSurvey;
    }

    public static void add(Survey survey) {
        System.out.println("Received survey for: " + survey.getSymbol());
        surveyMap.computeIfAbsent(survey.getSymbol(), i -> new ArrayList<>());
        surveyMap.get(survey.getSymbol()).add(survey);

        Set<WantedSurvey> matchedWants = new HashSet<>();
        for (WantedSurvey n : needSurvey) {
            if (survey.contains(n.getWanted())) {
                matchedWants.add(n);
            }
        }

        needSurvey.removeAll(matchedWants);
    }

    public static void addAll(List<Survey> surveys) {
        for (Survey survey : surveys) {
            add(survey);
        }
    }
}
