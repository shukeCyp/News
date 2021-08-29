package com.shuke.homepage.details.model.entity;

/**
 * @CreateDate: 2021/8/28 8:44
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.homepage.details.model.entity
 * @ClassName: DetailsEntity
 */

public class DetailsEntity  {

    public Integer id;
    public String title;
    public String content;
    public String url;
    public String newscode;
    public String auth;
    public String publishtime;
    public String isstaticpage;

    @Override
    public String toString() {
        return "DetailsEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", newscode='" + newscode + '\'' +
                ", auth='" + auth + '\'' +
                ", publishtime='" + publishtime + '\'' +
                ", isstaticpage='" + isstaticpage + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNewscode() {
        return newscode;
    }

    public void setNewscode(String newscode) {
        this.newscode = newscode;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getIsstaticpage() {
        return isstaticpage;
    }

    public void setIsstaticpage(String isstaticpage) {
        this.isstaticpage = isstaticpage;
    }

    public DetailsEntity() {
    }

    public DetailsEntity(Integer id, String title, String content, String url, String newscode, String auth, String publishtime, String isstaticpage) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.newscode = newscode;
        this.auth = auth;
        this.publishtime = publishtime;
        this.isstaticpage = isstaticpage;
    }
}
