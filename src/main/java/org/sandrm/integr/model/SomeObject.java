package org.sandrm.integr.model;

import java.io.Serializable;

/**
 * Created by sandr on 23.09.2018.
 */
public class SomeObject implements Serializable {

    private Integer id;
    private String description;

    public SomeObject() {

    }

    public SomeObject(Integer id, String description) {
        this.id = id;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SomeObject{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
