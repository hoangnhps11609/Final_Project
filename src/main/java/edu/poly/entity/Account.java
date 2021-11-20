package edu.poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Accounts")
public class Account  implements Serializable{
	@Id
	@NotEmpty
	@Length(min = 6)
	String username;

	@NotEmpty
	@Length(min = 6)
	String password;

	@NotBlank
	@Length(max = 50)
	String fullname;

	@NotEmpty
	@Email
	@Length(max = 50)
	String email;

	@NotEmpty
	String photo;

	@NotEmpty
	Boolean activated = true;

	@NotEmpty
	@Length(min=10)
	String phone;

	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	@PastOrPresent
	@NotEmpty
	Date createDate = new Date();
	

	@NotEmpty
	@Length(max = 250)
	String address;


	
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Blog> blogs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Comment> comments;
}
