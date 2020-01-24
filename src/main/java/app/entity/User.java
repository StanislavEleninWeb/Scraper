package app.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank
	@Email
	@Column(name = "username", nullable = false)
	private String username;

	@NotBlank
	@Size(min = 2)
	@Column(name = "first_name")
	private String firstName;

	@NotBlank
	@Size(min = 2)
	@Column(name = "last_name")
	private String lastName;

	@NotBlank
	@Size(min = 8, max = 8)
	@Column(name = "salt")
	private String salt;

	@NotBlank
	@Size(min = 60, max = 60)
	@Column(name = "password")
	private String password;

	@Column(name = "active", length = 1, columnDefinition = "TINYINT")
	private boolean active;

	@NotBlank
	@Size(min = 60, max = 60)
	@Column(name = "activation_code")
	private String activationCode;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private UserCriteria userCriteria;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(@NotBlank @Email String username, @NotBlank @Size(min = 2) String firstName,
			@NotBlank @Size(min = 2) String lastName, @NotBlank @Size(min = 8, max = 8) String salt,
			@NotBlank @Size(min = 60, max = 60) String password, boolean active,
			@NotBlank @Size(min = 60, max = 60) String activationCode) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salt = salt;
		this.password = password;
		this.active = active;
		this.activationCode = activationCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public UserCriteria getUserCriteria() {
		return userCriteria;
	}

	public void setUserCriteria(UserCriteria userCriteria) {
		this.userCriteria = userCriteria;
	}

}
