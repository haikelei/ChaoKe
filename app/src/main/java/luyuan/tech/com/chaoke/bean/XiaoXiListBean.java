package luyuan.tech.com.chaoke.bean;

/**
 * @author: lujialei
 * @date: 2019/6/25
 * @describe:
 */


public class XiaoXiListBean {

    /**
     * id : 9
     * title : 公告222
     * desc : 后方可进入国际快递发骨科大夫和
     * is_read : 1
     * createtime : 2019-06-24
     */

    private int id;
    private String title;
    private String desc;
    private int is_read;
    private String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
