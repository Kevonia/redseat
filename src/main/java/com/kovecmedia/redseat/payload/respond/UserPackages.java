package com.kovecmedia.redseat.payload.respond;

import java.util.List;

import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.Role;

public class UserPackages {
	
	
	private String name;
	private long id;
	private long points;
	
	private long role;
	
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
	public long getRole() {
		return role;
	}
	public void setRole(long role) {
		this.role = role;
	}
	
	
	

}
