package com.em.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "列表返回封装")
public class ListVoUntil<T>{
    @ApiModelProperty(value = "每一页显示条数")
    private String limit;
    @ApiModelProperty(value = "当前页数")
    private String page;
    @ApiModelProperty(value = "总计页数")
    private String pages;
    @ApiModelProperty(value = "数据总条数")
    private String total;
    private T list;
    public void setLimit(String limit) {
        this.limit = limit;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public void setPages(String pages) {
        this.pages = pages;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    public void setList(T list) {
        this.list = list;
    }
    public String getLimit() {
        return limit;
    }
    public String getPage() {
        return page;
    }
    public String getPages() {
        return pages;
    }
    public String getTotal() {
        return total;
    }
    public T getList() {
        return list;
    }
}