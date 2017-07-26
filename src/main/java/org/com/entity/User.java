package org.com.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity(name="users")
public class User {

	private Long userId;
	private String email;
	private String username;
	private String password;
	private String confirmPassword;
	private Boolean locked;
	private Boolean active;
	private List<Role> roles;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	public Long getId() {
		return userId;
	}
	public void setId(Long id) {
		this.userId = id;
	}
	@Column(name="email",nullable=false,updatable=false,unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="username",nullable=false,unique=true,length=30)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Column(name="locked",length=2)
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	@Column(name="active",length=2)
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@ManyToMany
	@JoinTable(name="user_role_mapping",
			   joinColumns=@JoinColumn(referencedColumnName="user_id",nullable=false),
			   inverseJoinColumns=@JoinColumn(referencedColumnName="role_id",nullable=false))
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + userId + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", locked=" + locked + ", active=" + active + ", roles="
				+ roles + "]";
	}
}
