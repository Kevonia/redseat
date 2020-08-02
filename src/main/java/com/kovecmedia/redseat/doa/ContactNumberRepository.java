package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kovecmedia.redseat.entity.ContactNumber;

@Repository
public interface ContactNumberRepository extends JpaRepository<ContactNumber, Long> {

}
