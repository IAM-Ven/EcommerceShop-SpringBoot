package io.github.thang86.forms;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
*  AddStoreProductForm.java
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
public class AddStoreProductForm {
	@NotNull
	private Long storeId;

	@NotNull
	private Long productId;

	@NotEmpty
	@Length(min = 3, max = 150)
	private String name;

	@NotEmpty
	@Length(min = 3, max = 1000)
	private String description;

	@NotNull
	@Min(0)
	private Float price;

	public AddStoreProductForm() {
	}

	public AddStoreProductForm(Long storeId, Long productId, String name, String description, Float price) {
		this.storeId = storeId;
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
