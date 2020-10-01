package com.atlantablog;

public class Blog {
	protected int id;
	protected String user;
	protected String blogEntry;
	
	Blog(){
		
	}
	Blog(int id, String user, String blogEntry){
		this.id = id;
		this.user = user;
		this.blogEntry = blogEntry;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getBlogEntry() {
		return blogEntry;
	}
	public void setBlogEntry(String blogEntry) {
		this.blogEntry = blogEntry;
	}
	
}
