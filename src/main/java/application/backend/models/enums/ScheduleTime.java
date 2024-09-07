package application.backend.models.enums;

public enum ScheduleTime {
    H07_00("07:00"),
    H08_00("08:00"),
    H09_00("09:00"),
    H10_00("10:00"),
    H11_00("11:00"),
    H12_00("12:00"),
    H13_00("13:00"),
    H14_00("14:00"),
    H15_00("15:00"),
    H16_00("16:00"),
    H17_00("17:00"),
    H18_00("18:00"),
    H19_00("19:00"),
    H20_00("20:00");

    private final String time;

    ScheduleTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
