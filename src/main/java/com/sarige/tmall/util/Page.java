package com.sarige.tmall.util;

import org.junit.Test;

public class Page {

    private int start;//开始页数
    private int count;//每页显示个数
    private int total;//总个数
    private String param;//参数

    private static final int defaultCount = 5;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Page() {
        count = defaultCount;
    }

    public Page(int start, int count) {
        this.start = start;
        this.count = count;
    }

    /**
     * 判断是否有上一页
     *
     * @return 表示是否有上一页的布尔值
     */
    public boolean isHasPreviouse() {
        return start != 0;
    }

    /**
     * 判断是否有下一页
     *
     * @return 表示是否有下一页的布尔值
     */
    public boolean isHasNext() {
        return start != getLast();
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public int getTotalPage() {
        int totalPage;
        if (0 == total % count) {
            totalPage = total / count;
        } else {
            totalPage = total / count + 1;
        }
        if (0 == totalPage) {
            totalPage = 1;
        }
        return totalPage;
    }

    /**
     * 获取最后一页的页码
     *
     * @return 最后一页的页码
     */
    public int getLast() {
        int last;
        if (0 == total / count) {
            last = total - count;
        } else {
            last = total - total % count;
        }
        return last;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", params='" + param + '\'' +
                '}';
    }

}
