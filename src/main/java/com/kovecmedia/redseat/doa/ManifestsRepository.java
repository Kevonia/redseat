package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Manifest;

public interface ManifestsRepository extends JpaRepository<Manifest, Long>  {

}
