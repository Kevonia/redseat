package com.kovecmedia.redseat.payload.request;

public class ResetRequest {

	String token;
	String newPassword;
	String confrimNewPassword;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfrimNewPassword() {
		return confrimNewPassword;
	}

	public void setConfrimNewPassword(String confrimNewPassword) {
		this.confrimNewPassword = confrimNewPassword;
	}

}
