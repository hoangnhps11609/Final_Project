package edu.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Colors")
@NoArgsConstructor
@AllArgsConstructor
public class Color implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	Integer id;
	
	@NotBlank
	@Length(max = 50)
	String name;
	
	@NotEmpty
	@Max(value = 255)
	Integer red;
	
	@NotEmpty
	@Max(value = 255)
	Integer green;
	
	@NotEmpty
	@Max(value = 255)
	Integer blue;
	
	@JsonIgnore
	@OneToMany(mappedBy = "color")
	List<ProductDetail> productDetails;
}
