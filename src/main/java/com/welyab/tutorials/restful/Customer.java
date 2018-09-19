package com.welyab.tutorials.restful;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.hateoas.Link;

@Entity
public class Customer implements Serializable {

    @Id
    private String code;

    private String name;

    private String email;

    @Transient
    private List<Link> links;

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

    public List<Link> getLinks() {
	return Collections.unmodifiableList(_getLinks());
    }

    private List<Link> _getLinks() {
	if (links == null) {
	    links = new ArrayList<>();
	}
	return links;
    }

    public void addLink(Link link) {
	_getLinks().add(link);
    }
}
