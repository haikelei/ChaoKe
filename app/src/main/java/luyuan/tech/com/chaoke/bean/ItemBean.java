package luyuan.tech.com.chaoke.bean;

import java.io.Serializable;

/**
 * @author: lujialei
 * @date: 2019/6/11
 * @describe:
 */


public class ItemBean implements Serializable {
    private String title;
    private boolean isChecked;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
