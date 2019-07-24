package luyuan.tech.com.chaoke.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/7/24
 * @describe:
 */


public class QianYueBeanTwo {
    private String old_id;
    private String house_type;
    private String reform_data;
    private String contract_type;
    private String renovation_begin;
    private String renovation_end;
    private String electric_max;
    private String electric_min;
    private String electric_num;
    private String total_electric;
    private List<String>  first_cost;

    public String getOld_id() {
        return old_id;
    }

    public void setOld_id(String old_id) {
        this.old_id = old_id;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getReform_data() {
        return reform_data;
    }

    public void setReform_data(String reform_data) {
        this.reform_data = reform_data;
    }

    public String getContract_type() {
        return contract_type;
    }

    public void setContract_type(String contract_type) {
        this.contract_type = contract_type;
    }

    public String getRenovation_begin() {
        return renovation_begin;
    }

    public void setRenovation_begin(String renovation_begin) {
        this.renovation_begin = renovation_begin;
    }

    public String getRenovation_end() {
        return renovation_end;
    }

    public void setRenovation_end(String renovation_end) {
        this.renovation_end = renovation_end;
    }

    public String getElectric_max() {
        return electric_max;
    }

    public void setElectric_max(String electric_max) {
        this.electric_max = electric_max;
    }

    public String getElectric_min() {
        return electric_min;
    }

    public void setElectric_min(String electric_min) {
        this.electric_min = electric_min;
    }

    public String getElectric_num() {
        return electric_num;
    }

    public void setElectric_num(String electric_num) {
        this.electric_num = electric_num;
    }

    public String getTotal_electric() {
        return total_electric;
    }

    public void setTotal_electric(String total_electric) {
        this.total_electric = total_electric;
    }

    public List<String> getFirst_cost() {
        return first_cost;
    }

    public void setFirst_cost(List<String> first_cost) {
        this.first_cost = first_cost;
    }
}
