package com.kovecmedia.redseat.service;
import org.springframework.transaction.annotation.Transactional;

import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.respond.PackageInvoice;
public interface PackegeService {

	 PackageInvoice getPacketByid(long id);
	 Package add(Package package1);
		@Transactional
	 void userPoint(Package package1,long point) throws Exception;
}
