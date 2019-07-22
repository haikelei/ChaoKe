package luyuan.tech.com.chaoke.bean;

/**
 * @author: lujialei
 * @date: 2019/7/20
 * @describe:
 */


public class PeiZhiBean {

    /**
     * id : 1
     * name : 厨具
     * icon : http://cloth.liebianzhe.com/static/upload/headimg/headimgurl.png
     * createtime : 1560387727
     */

    private int id;
    private String name;
    private String icon;
    private String createtime;
    public boolean checked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
