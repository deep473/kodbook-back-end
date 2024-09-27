package com.kodbook.entities;

import java.util.Arrays;
import java.util.Base64;

public class UserPosted {
		private String username;
		private byte[] profilePic;
		public UserPosted() {
			super();
			// TODO Auto-generated constructor stub
		}
		public UserPosted(String username, byte[] profilePic) {
			super();
			this.username = username;
			this.profilePic = profilePic;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public byte[] getProfilePic() {
			return profilePic;
		}
		public void setProfilePic(byte[] profilePic) {
			this.profilePic = profilePic;
		}
		public String getPhotoBase64() {
	        if (profilePic == null) {
	            return null;
	        }
	        return Base64.getEncoder().encodeToString(profilePic);
	    }
		@Override
		public String toString() {
			return "UserPosted [username=" + username + ", profilePic=" + Arrays.toString(profilePic) + "]";
		}
}
