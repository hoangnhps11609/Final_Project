package edu.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Brands")
public class Brand implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	Integer id;

	@NotBlank
	@Length(max = 50)
	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	List<Product> products;
}
