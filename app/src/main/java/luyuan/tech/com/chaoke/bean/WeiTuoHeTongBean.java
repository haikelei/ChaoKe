package luyuan.tech.com.chaoke.bean;

/**
 * @author: lujialei
 * @date: 2019/6/26
 * @describe:
 */


public class WeiTuoHeTongBean {

    /**
     * id : 1
     * state : 2
     * wuye_address : 北京市朝阳区
     * createtime : 2019/06/19
     */

    private int id;
    private int state;
    private String wuye_address;
    private String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getWuye_address() {
        return wuye_address;
    }

    public void setWuye_address(String wuye_address) {
        this.wuye_address = wuye_address;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
