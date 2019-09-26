package com.small.routing.smallrouting.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoInfo {
    private int id;
    private String phone_name;
    private String path;

    public PhotoInfo() {
    }

    public PhotoInfo(String name, String path) {
        this.phone_name = name;
        this.path = path;
    }

    public PhotoInfo(int id, String name, String path) {
        this.id = id;
        this.phone_name = name;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_name() {
        return phone_name;
    }

    public void setPhone_name(String phone_name) {
        this.phone_name = phone_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "PhotoInfo{" +
                "id=" + id +
                ", name='" + phone_name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
