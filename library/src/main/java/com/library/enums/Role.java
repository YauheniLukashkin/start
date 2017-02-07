package com.library.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	READER, LIBRARIAN, GUEST;
	Role() {}

	@Override
	public String getAuthority() {
		return "ROLE_" + name();
	}
}
