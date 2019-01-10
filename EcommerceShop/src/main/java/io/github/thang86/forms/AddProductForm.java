package io.github.thang86.forms;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
*  AddProductForm.java
* 
*  Version 1.0
*
*  Copyright
*
*  Modification Logs:
*  DATE		     AUTHOR		 DESCRIPTION
*  -------------------------------------
*  2018-12-12    ThangTX     Create
*/

@Component
public class AddProductForm {

	@NotEmpty
	@Length(max = 140, min = 3)
	private String name = "";

	@NotNull
	private Integer companyId;

	@NotNull
	private Integer brandId;

	@NotNull
	@Min(0)
	private Float averagePrice;

	@NotNull
	private Boolean isPhysicalProduct = true;

	//Nullity/Empty check will be done by the custom validatior
	@Min(0)
	private Float weight;

	@Min(0)
	private Float length;

	@Min(0)
	private Float width;

	@Min(0)
	private Float height;

	private String serial;

	public AddProductForm() {
	}

	public String getName() {
		return name;
	}

	public Float getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Float averagePrice) {
		this.averagePrice = averagePrice;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;


	}

	public Boolean getPhysicalProduct() {
		return isPhysicalProduct;
	}

	public void setPhysicalProduct(Boolean physicalProduct) {
		isPhysicalProduct = physicalProduct;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getLength() {
		return length;
	}

	public void setLength(Float length) {
		this.length = length;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
}
