package com.expertsoft.phoneshop.persistence.repository;

import com.expertsoft.phoneshop.persistence.model.user.PhoneShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<PhoneShopUser, String> {
	Optional<PhoneShopUser> findByUsername(String username);
}
