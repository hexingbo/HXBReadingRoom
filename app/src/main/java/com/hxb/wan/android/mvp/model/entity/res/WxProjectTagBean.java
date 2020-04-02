package com.hxb.wan.android.mvp.model.entity.res;

/**
 * @Author :hexingbo
 * @Date :2020/4/2
 * @FileName： WxProjectTagBean
 * @Describe :
 */
public  class WxProjectTagBean {
    /**
     * name : 项目
     * url : /project/list/1?cid=402
     */

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WxProjectTagBean{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}