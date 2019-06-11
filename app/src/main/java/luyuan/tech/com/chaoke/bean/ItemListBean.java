package luyuan.tech.com.chaoke.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author: lujialei
 * @date: 2019/6/11
 * @describe:
 */


public class ItemListBean implements Serializable{

    private ArrayList<ItemBean> list;
    public ArrayList<ItemBean> getList() {
        return list;
    }

    public void setList(ArrayList<ItemBean> list) {
        this.list = list;
    }


}
