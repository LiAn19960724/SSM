package com.insistla.ssm.pojo;

import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * page分页数据
 */
@Service("page")
public class Page implements Serializable {

    private Integer pageId;
    private String pageName;
    private String pageSex;
    private Integer pageAge;

    @Override
    public String toString() {
        return "Page{" +
                "pageId=" + pageId +
                ", pageName='" + pageName + '\'' +
                ", pageSex='" + pageSex + '\'' +
                ", pageAge=" + pageAge +
                '}';
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageSex() {
        return pageSex;
    }

    public void setPageSex(String pageSex) {
        this.pageSex = pageSex;
    }

    public Integer getPageAge() {
        return pageAge;
    }

    public void setPageAge(Integer pageAge) {
        this.pageAge = pageAge;
    }

    public Page(Integer pageId, String pageName, String pageSex, Integer pageAge) {
        this.pageId = pageId;
        this.pageName = pageName;
        this.pageSex = pageSex;
        this.pageAge = pageAge;
    }

    public Page() {
    }
}
