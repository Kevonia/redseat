package com.kovecmedia.redseat.payload.request;

public class InvoiceRequest {
	private String base64;
	private String name;
	private String type;
	private String username;
	private long packageid;
	
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getPackageid() {
		return packageid;
	}
	public void setPackageid(long packageid) {
		this.packageid = packageid;
	}

	
	
}
