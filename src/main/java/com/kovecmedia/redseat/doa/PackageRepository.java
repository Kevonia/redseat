package com.kovecmedia.redseat.doa;

import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {


	List<Package> findByUserId(User user);
}
