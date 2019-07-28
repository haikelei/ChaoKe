package luyuan.tech.com.chaoke.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author: lujialei
 * @date: 2019/6/22
 * @describe:
 */


public class ImageBean implements MultiItemEntity {
    private boolean isAddItem;
    private String path;

    public ImageBean() {
    }

    public String removeHost(String path){
        if (path.startsWith("http://rentpic.sayyin.com")){
            return path.replaceAll("http://rentpic.sayyin.com","");
        }
        return  path;
    }

    public ImageBean(String path) {
        String p = removeHost(path);
        this.path = p;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isAddItem() {
        return isAddItem;
    }

    public void setAddItem(boolean addItem) {
        isAddItem = addItem;
    }

    //0 默认图片item 1加号item
    @Override
    public int getItemType() {
        return isAddItem?1:0;
    }
}
