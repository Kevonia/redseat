package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.ContactNumber;


public interface ContactNumberRepository extends JpaRepository<ContactNumber, Long> {

}
