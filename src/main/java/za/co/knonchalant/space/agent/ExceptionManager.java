package za.co.knonchalant.space.agent;

import java.util.ArrayList;
import java.util.List;

public class ExceptionManager {
    private static final List<String> ERRORS = new ArrayList<>();

    public static void addError(String error) {
        ERRORS.add(error);
    }

    public static List<String> getErrors() {
        return ERRORS;
    }
}
