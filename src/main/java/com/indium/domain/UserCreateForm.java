package com.indium.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.indium.domain.Role;

public class UserCreateForm {

	@NotEmpty
	private String id = "";

	@NotEmpty
	private String password2 = "";

	@NotEmpty
	private String password1 = "";

	@NotNull
	private Role role = Role.USER;

	public String getId() {
		return id;
	}

	public void setId(String username) {
		this.id = username;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password) {
		this.password2 = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String passwordRepeated) {
		this.password1 = passwordRepeated;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
