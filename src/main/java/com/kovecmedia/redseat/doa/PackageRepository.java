package com.kovecmedia.redseat.doa;

import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.PackageLocation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PackageRepository extends JpaRepository<Package, Long> {
	List<Package> findByUserIdAndPreAlert(User user,boolean isPreAlert);

	@Query("SELECT p from packages p where p.userId =:user and p.location =:location")
	List<Package> findByUserIdandLocation(User user, PackageLocation location);


}
