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
public class ReportRevenueMonth implements Serializable {
	@Id
	Integer month;
	
	Integer year;
	
	Double revenue;
	
}