import java.time.LocalTime;

public abstract class Clock {
    protected int hh;
    protected int mm;
    protected int ss;
    private City city;

    protected Clock(City city) {
        this.city = city;
    }

    protected Clock() {
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        int diff = -this.city.getTimeZone() + city.getTimeZone();

        int newHour = this.hh + diff;
        if(newHour > 23){
            newHour -= 24;
        }
        if(newHour < 0){
            newHour += 24;
        }

        this.city = city;
    }

    public void setCurrentTime() {
        LocalTime obj = LocalTime.now();
        hh = obj.getHour();
        mm = obj.getMinute();
        ss = obj.getSecond();
    }

    public void setTime(int hours, int minutes, int seconds) {
        if (hh > 24 || hh < 0 || mm > 60 || mm < 0 || ss > 60 || ss < 0) {
            throw new IllegalArgumentException();
        }
        this.hh = hours;
        this.mm = minutes;
        this.ss = seconds;
    }

    @Override
    public String toString() {
        String hours;
        String minutes;
        String seconds;
        if (hh < 10) {
            hours = "0" + hh;
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
        return hours + ":" + minutes + ":" + seconds;
    }
}
