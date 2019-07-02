package luyuan.tech.com.chaoke.bean;

/**
 * @author: lujialei
 * @date: 2019/7/2
 * @describe:
 */


public class GeRenYeJiBean {

    /**
     * id : 65
     * nickname : 打爆你狗头
     * headimgurl : http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png
     * store_id : 12
     * store_name : 小猪
     * res_count : 2
     * is_self : 1
     */

    private int id;
    private String nickname;
    private String headimgurl;
    private int store_id;
    private String store_name;
    private int res_count;
    private int is_self;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public int getRes_count() {
        return res_count;
    }

    public void setRes_count(int res_count) {
        this.res_count = res_count;
    }

    public int getIs_self() {
        return is_self;
    }

    public void setIs_self(int is_self) {
        this.is_self = is_self;
    }
}
