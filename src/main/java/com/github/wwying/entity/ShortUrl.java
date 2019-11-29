package com.github.wwying.entity;



/**
 * @类名: 免票活动
 * @描述: 实体对象 免票活动
 * @author: yangzy
 * @日期: 2019-11-21 15:16:18
 */
public class ShortUrl {

    private static final long serialVersionUID = 3332782047292006569L;


    private Long id;
    //
    private String shortToken;
    //
    private String url;
    //

    private String enabled;


    private Integer hashCode;

    public Integer getHashCode() {
        return hashCode;
    }

    public void setHashCode(Integer hashCode) {
        this.hashCode = hashCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortToken() {
        return shortToken;
    }

    public void setShortToken(String shortToken) {
        this.shortToken = shortToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
