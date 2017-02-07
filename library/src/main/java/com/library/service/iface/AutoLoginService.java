package com.library.service.iface;


import com.library.enums.Role;

public interface AutoLoginService {
	void autoLogin(String username, String password, Role role);
}
