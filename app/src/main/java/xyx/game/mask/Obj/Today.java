package xyx.game.mask.Obj;

import java.io.Serializable;

/**一天发布一次+一天加载一次**/
public class Today implements Serializable {
    private Long send;
    private Long load;

    public Today() {
    }

    public Today(Long send, Long load) {
        this.send = send;
        this.load = load;
    }

    public Long getSend() {
        return send;
    }

    public void setSend(Long send) {
        this.send = send;
    }

    public Long getLoad() {
        return load;
    }

    public void setLoad(Long load) {
        this.load = load;
    }
}
