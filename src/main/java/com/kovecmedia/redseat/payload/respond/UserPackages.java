package com.kovecmedia.redseat.payload.respond;

import java.util.List;

import com.kovecmedia.redseat.entity.Package;

public class UserPackages {
	
	
	private String name;
	private long id;
	private long points;
	
	private List<Package> packageslist;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Package> getPacklist() {
		return packageslist;
	}
	public void setPacklist(List<Package> packageslist) {
		this.packageslist = packageslist;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPoints() {
		return points;
	}
	public void setPoints(long points) {
		this.points = points;
	}
	
	
	

}
