package com.welyab.tutorials.restful.api;

public class Link {

    private final String link;
    private final String rel;
    private final String type;

    public Link(String link, String rel, String type) {
	this.link = link;
	this.rel = rel;
	this.type = type;
    }

    public String getLink() {
	return link;
    }

    public String getRel() {
	return rel;
    }

    public String getType() {
	return type;
    }
}
