package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kovecmedia.redseat.entity.User;

/**
 * User repository for CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
