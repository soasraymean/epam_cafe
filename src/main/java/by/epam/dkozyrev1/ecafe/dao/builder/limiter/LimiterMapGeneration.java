package by.epam.dkozyrev1.ecafe.dao.builder.limiter;

import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.HashMap;
import java.util.Map;

public final class LimiterMapGeneration {

    private LimiterMapGeneration() {

    }

    @CheckedArguments
    public static Map<String, Limiter> generateOfSingleType(Limiter limiterType, String... args){
        HashMap<String, Limiter> limiterHashMap = new HashMap<>();
        for (String arg : args) {
            limiterHashMap.put(arg, limiterType);
        }
        return limiterHashMap;
    }

}