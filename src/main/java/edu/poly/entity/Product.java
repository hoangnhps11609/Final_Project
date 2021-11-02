package edu.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity @Table(name = "Products")
public class Product  implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	

	@NotBlank
	@Length(max = 50)
	String name;

	@NotEmpty
	Double price;

	@NotEmpty
	@Length(max = 255)
	String description;

	@NotEmpty
	@Max(value =100)
	Integer discount;

	@NotNull
	Boolean available;

	@NotBlank
	@Length(max = 50)
	String image;
	@ManyToOne
	@JoinColumn(name = "Genderid")
	Gender gender;
	
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	
	@ManyToOne
	@JoinColumn(name = "Brandid")
	Brand brand;
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<ProductDetail> productDetails;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Comment> comments;	
}
