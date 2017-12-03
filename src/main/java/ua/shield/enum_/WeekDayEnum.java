package ua.shield.enum_;

/**
 * Created by sa on 30.11.17.
 */
public enum WeekDayEnum {
    MONDEY("ПН"),TUESDAY("ВТ"),WEDNESDAY("СР"),
    THUSDAY("ЧТ"),FRIDAY("ПТН"),SATURDAY("СБ"),
    SANDAY("ВС");
    private String value;

    WeekDayEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
