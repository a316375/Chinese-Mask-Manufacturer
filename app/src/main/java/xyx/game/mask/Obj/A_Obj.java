package xyx.game.mask.Obj;

public class A_Obj {
    public A_Obj() {}

    private String Info;private Long ID;
    private Integer Times,Year,Gender;


    public A_Obj(String info, Integer times, Long ID, Integer year, Integer gender) {
        Info = info;
        Times = times;
        this.ID = ID;
        Year = year;
        Gender = gender;
    }

    @Override
    public String toString() {
        return "A_Obj{" +
                "Info='" + Info + '\'' +
                ", ID='" + ID + '\'' +
                ", Times=" + Times +
                ", Year=" + Year +
                ", Gender=" + Gender +
                '}';
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Integer getTimes() {
        return Times;
    }

    public void setTimes(Integer times) {
        Times = times;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public Integer getGender() {
        return Gender;
    }

    public void setGender(Integer gender) {
        Gender = gender;
    }
}
