package luyuan.tech.com.chaoke.bean;

import java.io.Serializable;

/**
 * @author: lujialei
 * @date: 2019/6/19
 * @describe:
 */


public class HeTongIdBean implements Serializable{

    /**
     * con_id : 合同id
     * cons_id : 分合同ID
     */

    private String con_id;
    private String cons_id;

    public String getCon_id() {
        return con_id;
    }

    public void setCon_id(String con_id) {
        this.con_id = con_id;
    }

    public String getCons_id() {
        return cons_id;
    }

    public void setCons_id(String cons_id) {
        this.cons_id = cons_id;
    }
}
