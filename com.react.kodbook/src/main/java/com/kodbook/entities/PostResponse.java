package com.kodbook.entities;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class PostResponse {
	private Long id;
	private String caption;
	private int likes;
	private List<String> comments;
	private byte[] photo;
	private UserPosted user;
	public PostResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PostResponse(Long id, String caption, int likes, List<String> comments, byte[] photo, UserPosted user) {
		super();
		this.id = id;
		this.caption = caption;
		this.likes = likes;
		this.comments = comments;
		this.photo = photo;
		this.user = user;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public UserPosted getUser() {
		return user;
	}

	public void setUser(UserPosted user) {
		this.user = user;
	}

	public String getPhotoBase64() {
        if (photo == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(photo);
    }
	@Override
	public String toString() {
		return "PostResponse [id=" + id + ", caption=" + caption + ", likes=" + likes + ", comments=" + comments
				+ ", photo=" + Arrays.toString(photo) + ", user=" + user + "]";
	}

}
