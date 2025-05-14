public class DigitalClock extends Clock {

    private final ClockType type;

    private DigitalClock(ClockType type) {
        super();
        this.type = type;
    }

    @Override
    public String toString() {
        if (type == ClockType.H24) {
            return super.toString();
        }
        String hours;
        String minutes;
        String seconds;
        if (hh > 12) {
            hours = String.valueOf(hh - 12);
        } else {
            hours = String.valueOf(hh);
        }
        if (mm < 10) {
            minutes = "0" + mm;
        } else {
            minutes = String.valueOf(mm);
        }
        if (ss < 10) {
            seconds = "0" + ss;
        } else {
            seconds = String.valueOf(ss);
        }
        return hours + ":" + minutes + ":" + seconds + " " + (hh > 12 ? "PM" : "AM");
    }

    public enum ClockType {
        H24, H12;
    }
}
