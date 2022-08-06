package com.kovecmedia.redseat.payload.respond;

public class PackageLabel {

	String  packageDescrtion;
	String  seller;
	double  weigth;
	java.util.Date labelDate;
	
	String localAddress;
	String localPhone;
	String localEmail;
	
	
	String fromName;
	String fromAddressline1;
	String fromAddressline2;
	
	String toName;
	String ToUser;

	byte[] qrTop;
	byte[] qrPackage;
	
	
	public String getPackageDescrtion() {
		return packageDescrtion;
	}
	public void setPackageDescrtion(String packageDescrtion) {
		this.packageDescrtion = packageDescrtion;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public double getWeigth() {
		return weigth;
	}
	public void setWeigth(double weigth) {
		this.weigth = weigth;
	}
	public java.util.Date getLabelDate() {
		return labelDate;
	}
	public void setLabelDate(java.util.Date labelDate) {
		this.labelDate = labelDate;
	}
	public String getLocalAddress() {
		return localAddress;
	}
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
	public String getLocalPhone() {
		return localPhone;
	}
	public void setLocalPhone(String localPhone) {
		this.localPhone = localPhone;
	}
	public String getLocalEmail() {
		return localEmail;
	}
	public void setLocalEmail(String localEmail) {
		this.localEmail = localEmail;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromAddressline1() {
		return fromAddressline1;
	}
	public void setFromAddressline1(String fromAddressline1) {
		this.fromAddressline1 = fromAddressline1;
	}
	public String getFromAddressline2() {
		return fromAddressline2;
	}
	public void setFromAddressline2(String fromAddressline2) {
		this.fromAddressline2 = fromAddressline2;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getToUser() {
		return ToUser;
	}
	public void setToUser(String toUser) {
		ToUser = toUser;
	}
	public byte[] getQrTop() {
		return qrTop;
	}
	public void setQrTop(byte[] qrTop) {
		this.qrTop = qrTop;
	}
	public byte[] getQrPackage() {
		return qrPackage;
	}
	public void setQrPackage(byte[] qrPackage) {
		this.qrPackage = qrPackage;
	}
	
	
	
}
