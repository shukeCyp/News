package com.shuke.homepage.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @ClassName NewsTypeEntity
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/22 20:22
 * @Version 1.0
 */
public class NewsTypeEntity {
    private int code;
    private List<NewsTypeEntity.DataBean> data;
    private String msg;

    public NewsTypeEntity() {
    }

    public NewsTypeEntity(int code, List<DataBean> data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean{

        private int id;
        private String typename;

        public DataBean() {
        }

        public DataBean(int id, String typename) {
            this.id = id;
            this.typename = typename;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", typename='" + typename + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsTypeEntity{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
