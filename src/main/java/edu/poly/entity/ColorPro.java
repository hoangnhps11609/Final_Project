package edu.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ColorPro implements Serializable {
	@Id
	Integer id;
	
	String name;
	
	Integer red;
	
	Integer green;
	
	Integer blue;
}
