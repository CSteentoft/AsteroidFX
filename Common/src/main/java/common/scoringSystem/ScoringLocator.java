package common.scoringSystem;

public class ScoringLocator {
    private static IScoringService SERVICE;

    private ScoringLocator() {}

    public static void setService(IScoringService svc) {
        SERVICE = svc;
    }

    public static IScoringService getService() {
        if (SERVICE == null) {
            throw new IllegalStateException(
                    "No IScoringService registered! Call ScoringLocator.setService(...) first."
            );
        }
        return SERVICE;
    }
}
