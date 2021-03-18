 package com.spring.core.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String username;
	
	private String password;
	
	private String confirmPassword;
	
	private boolean enabled;
	
	private String emailId;
	
	private String country;

	public final String getEmailId() {
		return emailId;
	}

	public final void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	
	/**
	 * **userRole**
	 */
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	/**
	 * **User Constructor**.
	 */
	public User() {
	}

	public User(String username, String password, boolean enabled,
	  final String emailId) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.emailId = emailId;
	}

	public User(final String username, String password, final boolean enabled,
	  final Set<UserRole> userRole,
			final String emailId) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
		this.emailId = emailId;
	}

	@Column(name = "username", unique = true, nullable = false, length = 45)
	@ApiModelProperty(notes = "In database it will generat id")
 public final String getUsername() {
		return this.username;
	}

	public final void setUsername(final String username) {
		this.username = username;
	}

	@ApiModelProperty(notes = "Password of user")
	@Column(name = "password", nullable = false, length = 45)
 public final String getPassword() {
		return this.password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	@ApiModelProperty(notes = "Status of login")
	@Column(name = "enabled", nullable = false)
 public final boolean isEnabled() {
		return this.enabled;
	}

	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@ApiModelProperty(notes = "Role Id of user")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
 public final Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public final void setUserRole(final Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	@Column(name = "confirmPassword", nullable = false, length = 45)
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Column(name = "country", nullable = false, length = 45)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}