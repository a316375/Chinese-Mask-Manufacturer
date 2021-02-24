package xyx.game.mask.Obj;

import androidx.annotation.Keep;

import java.io.Serializable;
@Keep
public class Load implements Serializable {
    private String name;
    private A_Obj a_obj;

    public Load() {
    }

    public Load(String name, A_Obj a_obj) {
        this.name = name;
        this.a_obj = a_obj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A_Obj getA_obj() {
        return a_obj;
    }

    public void setA_obj(A_Obj a_obj) {
        this.a_obj = a_obj;
    }
}
