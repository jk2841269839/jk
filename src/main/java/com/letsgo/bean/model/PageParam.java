package com.letsgo.bean.model;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/6/22 3:02 PM
 */
public class PageParam {
    /**
     * index
     */
    private Integer index = 0;
    /**
     * 页码号
     */
    private Integer pageNum;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 排序字段
     */
    private String orderBy;
    /**
     * Ascending descending 默认是升序
     */
    private Enum<OrderBy> ascOrDesc = OrderBy.ASC;

    public PageParam() {
        this(1, 5);
    }

    public PageParam(Integer pageNum, Integer pageSize) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.index = (pageNum - 1) * pageSize;
    }

    public PageParam(Integer pageNum, Integer pageSize, String orderBy) {
        this(pageNum, pageSize, orderBy, OrderBy.ASC);
    }

    public PageParam(Integer pageNum, Integer pageSize, String orderBy, Enum<OrderBy> ascOrDesc) {
        this(pageNum, pageSize);
        this.orderBy = ascOrDesc.toString();
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        this.index = (pageNum - 1) * pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Enum<OrderBy> getAscOrDesc() {
        return ascOrDesc;
    }

    public void setAscOrDesc(Enum<OrderBy> ascOrDesc) {
        this.ascOrDesc = ascOrDesc;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
