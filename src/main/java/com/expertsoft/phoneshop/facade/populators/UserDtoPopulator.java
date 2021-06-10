package com.expertsoft.phoneshop.facade.populators;

import com.expertsoft.phoneshop.dto.UserDto;
import com.expertsoft.phoneshop.persistence.model.user.PhoneShopUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Component
public class UserDtoPopulator implements Function<PhoneShopUser, UserDto> {

	private static final String UNKNOWN = "unknown";

	@Value("${phoneshop.date.format}")
	private String DATE_PATTERN;

	@Override
	public UserDto apply(PhoneShopUser phoneShopUser) {
		if (phoneShopUser == null) {
			throw new IllegalArgumentException("phoneShopUser cannot be empty");
		}
		UserDto userDto = new UserDto();
		userDto.setName(phoneShopUser.getUsername());
		userDto.setLocation(phoneShopUser.getLocation());

		LocalDateTime date = phoneShopUser.getRegistrationDate();
		if (date != null) {
			userDto.setRegistrationDate(date.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
		} else {
			userDto.setRegistrationDate(UNKNOWN);
		}
		return userDto;
	}
}
