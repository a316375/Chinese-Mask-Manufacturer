package xyx.game.mask.Obj;

import java.io.Serializable;

public class User    {
    public User( ) {

    }

    int Gender;
    int Year;
    String string;
    long time;
    String email;

    public User(int gender, int year, String string, long time,String email) {
        this.email=email;
        Gender = gender;
        Year = year;
        this.string = string;
        this.time = time;

    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
