package application.backend.models.enums;

public enum ProgramDuration {
    FOUR_WEEKS(4),
    EIGHT_WEEKS(8),
    TWELVE_WEEKS(12);

    private final int weeks;

    ProgramDuration(int weeks) {
        this.weeks = weeks;
    }

    public int getWeeks() {
        return weeks;
    }
}