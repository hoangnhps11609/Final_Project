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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Sizedetail")
public class SizeDetail implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String description;
	
	@ManyToOne
	@JoinColumn(name = "Sizeid")
	Size size;
	
	@ManyToOne
	@JoinColumn(name = "Productsid")
	Product product;
}
