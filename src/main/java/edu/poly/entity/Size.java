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

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Sizes")
public class Size implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@NotBlank
	@Length(max = 50)
	String name;
	
	@Length(max = 250)
	String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "size")
	List<ProductDetail> productDetails;
}
