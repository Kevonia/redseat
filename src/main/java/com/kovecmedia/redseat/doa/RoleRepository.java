package com.kovecmedia.redseat.doa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kovecmedia.redseat.entity.Role;
import com.kovecmedia.redseat.model.Roles;

public interface RoleRepository extends JpaRepository<Role, Long>   {
	Optional<Role> findByName(Roles name);
}
