package com.kovecmedia.redseat.doa;

import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.PackageLocation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface PackageRepository extends JpaRepository<Package, Long> {
	List<Package> findByUserIdAndPreAlert(User user,boolean isPreAlert);

	List<Package> findByUserId(User user);
	
	List<Package> findByUserIdAndEmailSent(User user, boolean email_sent);
    
	Boolean existsByTrackingNumber(String trackno);
	
	List<Package> findByEmailSent(Boolean stauts);
	
	@Procedure("sp_UserValue")
	long getValuebyUser(long userID);

}
