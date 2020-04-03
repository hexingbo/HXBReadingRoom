package com.hxb.wan.android.mvp.model.entity.res;

import java.util.List;

/**
 * @Author :hexingbo
 * @Date :2020/4/3
 * @FileName： MyCollectListData
 * @Describe :
 */
public class MyCollectListData {


    /**
     * curPage : 1
     * datas : [{"author":"zskingking","chapterId":402,"chapterName":"跨平台应用","courseId":13,"desc":"App基于MVP架构，全部使用Kotlin语言开发，基本全部实现Wan Android API对应所有功能，后续会增加额外功能，欢迎关注，欢迎star。","envelopePic":"https://www.wanandroid.com/blogimgs/3b1ee979-bc7d-43d7-8cd9-af089936a001.png","id":124554,"link":"http://www.wanandroid.com/blog/show/2723","niceDate":"1天前","origin":"","originId":12564,"publishTime":1585816542000,"title":"Kotlin版玩安卓，超级简洁的界面，沉浸式的体验让你纵享丝滑。","userId":18118,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 1
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<MyCollectedBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MyCollectedBean> getDatas() {
        return datas;
    }

    public void setDatas(List<MyCollectedBean> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "MyCollectListData{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                ", datas=" + datas +
                '}';
    }
}
