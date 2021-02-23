package xyx.game.mask.GreenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Users {
    @Id  @Unique  // @Id(autoincrement = true)
    private String id;

    private int Gender;
    private int Year;
    private  String info;
    private  long time;
    private boolean isread;
    @Generated(hash = 509080280)
    public Users(String id, int Gender, int Year, String info, long time,
            boolean isread) {
        this.id = id;
        this.Gender = Gender;
        this.Year = Year;
        this.info = info;
        this.time = time;
        this.isread = isread;
    }
    @Generated(hash = 2146996206)
    public Users() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getGender() {
        return this.Gender;
    }
    public void setGender(int Gender) {
        this.Gender = Gender;
    }
    public int getYear() {
        return this.Year;
    }
    public void setYear(int Year) {
        this.Year = Year;
    }
    public String getInfo() {
        return this.info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public boolean getIsread() {
        return this.isread;
    }
    public void setIsread(boolean isread) {
        this.isread = isread;
    }



}