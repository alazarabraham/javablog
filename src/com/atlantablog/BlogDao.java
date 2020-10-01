package com.atlantablog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BlogDao {
	
	private String jdbcUrl;
	private String jdbcUser;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public BlogDao(String jdbcUrl,String jdbcUser,String jdbcPassword) {
		this.jdbcUrl = jdbcUrl;
		this.jdbcUser = jdbcUser;
		this.jdbcPassword = jdbcPassword;
		
	}
	
	public void connect() throws SQLException{
		if(jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e){
				throw new SQLException(e);
				
			}
			jdbcConnection = DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPassword);
		}
	}
	
	public void disconnect()throws SQLException{
		if(jdbcConnection !=null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertBlog(Blog blog)throws SQLException{
		String sql = "insert into blog(user,blogentry)values(?,?)" ;
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1,blog.getUser());
		statement.setString(2, blog.getBlogEntry());
		boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
	}
	
	public List<Blog> listAllBlogs()throws SQLException{
		
		List<Blog> listBlog = new ArrayList<>();
		String sql = "select id,user,blogentry from blog";
		connect();
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultset = statement.executeQuery(sql);
		while(resultset.next()) {
			int id = resultset.getInt("id");
			String user = resultset.getString("user");
			String blogentry = resultset.getString("blogentry");
			
			Blog blog = new Blog(id,user,blogentry);
			listBlog.add(blog);
		}
		resultset.close();
        statement.close();
         
        disconnect();
         
        return listBlog;
	}
	
}
