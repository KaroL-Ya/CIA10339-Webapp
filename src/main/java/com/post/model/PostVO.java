package com.post.model;
import java.sql.Date;

public class PostVO implements java.io.Serializable{
	private Integer postId;
	private Integer cafeId;
	private Integer memId;
	private Date time;
	private String title;
	private String  content;
	private Integer count;
	private Byte status ;
	
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getCafeId() {
		return cafeId;
	}
	public void setCafeId(Integer cafeId) {
		this.cafeId = cafeId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
}
