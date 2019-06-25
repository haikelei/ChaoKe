package luyuan.tech.com.chaoke.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/6/20
 * @describe:
 */


public class ZuKeDetailBean {

    /**
     * id : 3
     * username : 小猪猪
     * sex : 2
     * phone : 13400000000
     * source : 1
     * grade : 1
     * rent_min : 800
     * rent_max : 1600
     * room : 3
     * office : 1
     * toilet : 2
     * people_num : 5
     * checkin_time : 2019-09-22
     * desc : 了法国韩国进口的发几个开发贷款机构的发挥
     * reason : null
     * status : 1
     * city_name : 海淀区
     * tenant_num : 2147483647
     * follow_data : [{"follow":1,"desc":"劈里啪啦发货的接口返回东京看发动机","createtime":"2019/06/14 15:50"}]
     */

    private int id;
    private String username;
    private int sex;
    private String phone;
    private int source;
    private int grade;
    private String rent_min;
    private String rent_max;
    private int room;
    private int office;
    private int toilet;
    private int people_num;
    private String checkin_time;
    private String desc;
    private Object reason;
    private int status;
    private String city_name;
    private int tenant_num;
    private List<FollowDataBean> follow_data;
    public String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getRent_min() {
        return rent_min;
    }

    public void setRent_min(String rent_min) {
        this.rent_min = rent_min;
    }

    public String getRent_max() {
        return rent_max;
    }

    public void setRent_max(String rent_max) {
        this.rent_max = rent_max;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public int getToilet() {
        return toilet;
    }

    public void setToilet(int toilet) {
        this.toilet = toilet;
    }

    public int getPeople_num() {
        return people_num;
    }

    public void setPeople_num(int people_num) {
        this.people_num = people_num;
    }

    public String getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(String checkin_time) {
        this.checkin_time = checkin_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getTenant_num() {
        return tenant_num;
    }

    public void setTenant_num(int tenant_num) {
        this.tenant_num = tenant_num;
    }

    public List<FollowDataBean> getFollow_data() {
        return follow_data;
    }

    public void setFollow_data(List<FollowDataBean> follow_data) {
        this.follow_data = follow_data;
    }

    public static class FollowDataBean {
        /**
         * follow : 1
         * desc : 劈里啪啦发货的接口返回东京看发动机
         * createtime : 2019/06/14 15:50
         */

        private int follow;
        private String desc;
        private String createtime;

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
