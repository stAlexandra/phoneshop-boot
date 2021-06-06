package com.expertsoft.phoneshop.persistence.repository;

import com.expertsoft.phoneshop.persistence.model.PhoneShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneShopUserRepository extends JpaRepository<PhoneShopUser, String> {
	Optional<PhoneShopUser> findByUsername(String username);
}
