package com.example.demo.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "stores")
public class StoreEntity extends BaseEntity{
    private String address;
    private String name;
    private String url;

    public StoreEntity() {
    }

    @Column(name = "name",nullable = false,unique = true)
    @NotNull
    @Size(min = 5,max = 35)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "picture_url",nullable = false,columnDefinition = "TEXT")
    @NotNull
    @Size(min = 5)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "addresss",nullable = false)
    @NotNull
    @Size(min = 5,max = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
