package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kovecmedia.redseat.entity.User;
import java.util.Optional;

/**
 * User repository for CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);
}
