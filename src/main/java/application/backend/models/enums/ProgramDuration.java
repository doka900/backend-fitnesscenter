package application.backend.models.enums;

public enum ProgramDuration {
    ČETIRI_NEDELJE(4),
    OSAM_NEDELJA(8),
    DVANAEST_NEDELJA(12);

    private final int weeks;

    ProgramDuration(int weeks) {
        this.weeks = weeks;
    }

    public int getWeeks() {
        return weeks;
    }
}

