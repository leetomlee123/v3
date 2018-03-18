package com.lx.entity;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {


    private static final long serialVersionUID = 337297181251071639L;

    private Integer currentPage;//当前页
    private Integer pageSize;//页大小
    private Integer totalPage;// 总记录 数
    private List<T> list;//页面数据列表
    private String keyWord;//查询关键字
    private T paramEntity;//多条件查询
    private Integer start;//需要这里处理
    private Integer totalCount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    //    private Map<String, Object> pageMap = new HashMap<String, Object>();
//
//    public Map<String, Object> getPageMap() {
//        return pageMap;
//    }
//
//    /*public void setPageMap(Map<String, Object> pageMap) {
//        this.pageMap = pageMap;
//    }*/
    public T getParamEntity() {
        return paramEntity;
    }

    public void setParamEntity(T paramEntity) {
        this.paramEntity = paramEntity;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        if (totalCount == 0)
            totalPage = 0;
        if (totalCount % pageSize == 0)
            totalPage = totalCount / pageSize;
        else
            totalPage = totalCount / pageSize + 1;
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {

        this.list = list;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getStart() {
        this.start = (currentPage - 1) * pageSize;
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }


}
