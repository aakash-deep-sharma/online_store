package org.com.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name="roles")
public class Role {

	private Long roleId;
	private String roleName;
	private Set<User> users;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	public Long getId() {
		return roleId;
	}
	public void setId(Long id) {
		this.roleId = id;
	}
	@Column(name="role",nullable=false,unique=true)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@ManyToMany(mappedBy="roles")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Role [id=" + roleId + ", roleName=" + roleName + "]";
	}
}
