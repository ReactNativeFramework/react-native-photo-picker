package com.atide.photopicker.beans;

import java.io.Serializable;

/**
 * @Class: Photo
 * @Description: 照片实体
 *
 */
public class Photo implements Serializable {

    private int id;
    private String path;  //路径

    public Photo(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
