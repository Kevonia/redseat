package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kovecmedia.redseat.entity.ForgetPassword;
import com.kovecmedia.redseat.entity.User;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword, Long> {

	ForgetPassword findByUsedAndUser(boolean used, User user);

	ForgetPassword findByToken(String token);

	boolean existsByToken(String token);
}
