package xyx.game.mask.Obj;

import androidx.annotation.Keep;

import java.io.Serializable;

/**储存个人信息**/
@Keep
public class User    {
    public User( ) {

    }

    int gender;
    int year;
    String string;
    long time;
    String email;

    public User(int gender, int year, String string, long time,String email) {
        this.email=email;
        this.gender = gender;
        this.year = year;
        this.string = string;
        this.time = time;

    }

    @Override
    public String toString() {
        return "User{" +
                "gender=" + gender +
                ", year=" + year +
                ", string='" + string + '\'' +
                ", time=" + time +
                ", email='" + email + '\'' +
                '}';
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
