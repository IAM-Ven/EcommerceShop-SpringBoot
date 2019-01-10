package io.github.thang86.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
*  UserCreateForm.java
* 
*  Version 1.0
*
*  Copyright
*
*  Modification Logs:
*  DATE		     AUTHOR		 DESCRIPTION
*  -------------------------------------
*  2018-12-12    ThangTX     Create
*/

public class UserCreateForm {

	@NotEmpty
	@Length(min = 2, max = 50)
	private String name = "";

	@NotEmpty
	@Length(min = 5, max = 36)
	private String username = "";

	@NotEmpty
	@Length(min = 2, max = 40)
	@Email
	private String email = "";

	@NotEmpty
	@Length(min = 2, max = 120, message = "Password must be greater than or equal 6 characters.")
	private String password = "";

	@NotEmpty
	private String passwordConfirm = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
