package xyx.game.mask.Obj;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
/**发布时候拿到排序**/
public class Num   {
    private Long num;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
    public Num() {

    }
    public Num(Long num) {
        this.num = num;
    }
}
