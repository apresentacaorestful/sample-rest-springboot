package com.welyab.tutorials.restful;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.welyab.tutorials.restful.api.Link;

@Entity
public class Customer implements Serializable {

    @Id
    private String code;

    private String name;

    private String email;

    @Transient
    private Link self;

    @Transient
    private Link delete;

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Link getSelf() {
	return self;
    }

    public void setSelf(Link self) {
	this.self = self;
    }

    public Link getDelete() {
	return delete;
    }

    public void setDelete(Link delete) {
	this.delete = delete;
    }
}
