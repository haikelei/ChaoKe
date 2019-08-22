package luyuan.tech.com.chaoke.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/8/22
 * @describe:
 */


public class MianZuCelueBean {

    /**
     * id : 1
     * total_signing_id : 合同ID
     * type : 1
     * data : ["输入的天数","输入的天数"]
     * createtime :
     * updatetime :
     */

    private int id;
    private String total_signing_id;
    private String type;
    private String createtime;
    private String updatetime;
    private List<String> data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTotal_signing_id() {
        return total_signing_id;
    }

    public void setTotal_signing_id(String total_signing_id) {
        this.total_signing_id = total_signing_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
