package com.kovecmedia.redseat.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Manifest;
import com.kovecmedia.redseat.entity.ManifestItems;

public interface ManifestsItemRepository extends JpaRepository<ManifestItems, Long>  {

	List<ManifestItems> findByManifestId(Manifest manifest);
	
	
}
