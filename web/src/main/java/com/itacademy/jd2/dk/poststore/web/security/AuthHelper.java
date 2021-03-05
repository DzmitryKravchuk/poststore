package com.itacademy.jd2.dk.poststore.web.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {

	private AuthHelper() {
	}

	public static Integer getLoggedUserId() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		if (!(auth instanceof ExtendedUsernamePasswordAuthenticationToken)) {
			return null;
		}

		ExtendedUsernamePasswordAuthenticationToken extendedAuth = (ExtendedUsernamePasswordAuthenticationToken) auth;
		return extendedAuth.getId();
	}

	public static Set<String> getLoggedUserRoles() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		Set<String> roleNames = new HashSet<>();
		for (GrantedAuthority grantedAuthority : authorities) {
			roleNames.add(grantedAuthority.getAuthority());
		}
		return roleNames;
	}

	public static boolean hasRole(String roleName) {
		return getLoggedUserRoles().contains("ROLE_" + roleName);
	}

	public static boolean isAdmin() {
		return hasRole("admin");
	}
}
