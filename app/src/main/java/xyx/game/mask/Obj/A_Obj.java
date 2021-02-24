package xyx.game.mask.Obj;

import androidx.annotation.Keep;

import java.io.Serializable;

/**发布到公布栏**/
@Keep
public class A_Obj   {

    public A_Obj() {}

    private String info;private Long id;
    private Integer times; private Integer year; private Integer gender;


    public A_Obj(String info, Integer times, Long id, Integer year, Integer gender) {
        this.info = info;
        this.times = times;
        this.id = id;
        this.year = year;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "A_Obj{" +
                "info='" + info + '\'' +
                ", id=" + id +
                ", times=" + times +
                ", year=" + year +
                ", gender=" + gender +
                '}';
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
