package com.kovecmedia.redseat.service;
import com.kovecmedia.redseat.payload.respond.PackageInvoice;
import com.kovecmedia.redseat.entity.Package;
public interface PackegeService {

	 PackageInvoice getPacketByid(long id);
	 Package add(Package package1);
	 
}
