package luyuan.tech.com.chaoke.bean;

/**
 * @author: lujialei
 * @date: 2019/6/20
 * @describe:
 */


public class HeTongDetailBean {

    /**
     * address : 当前物业地址，当前物业地址，当前物业地址，当前物业地址，当前物业地址，当前物业地址
     * phone : 18988888888
     * username : 承租人，承租人，承租人，承租人，承租人，承租人，承租人，承租人，承租人，承租人，承租人
     * subtime : 2019-06-20 00:00:00
     * state : 1
     * mode : 0
     * qrcode :
     */

    private String address;
    private String phone;
    private String username;
    private String subtime;
    private int state;
    private int mode;
    private String qrcode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubtime() {
        return subtime;
    }

    public void setSubtime(String subtime) {
        this.subtime = subtime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
