package io.github.thang86.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.thang86.enums.StoreStatus;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
*  Store.java
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


@Entity
@Table(name = "store")
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "storeType")
public abstract class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	protected Long id;

	@Column(name = "name", nullable = false, unique = true)
	@Field
	protected String name;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	protected StoreStatus status;

	@ManyToOne(optional = false)
	@JsonBackReference
	protected StoreOwner storeOwner;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "collaboratedStores")
	protected Set<StoreOwner> collaborators;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "store", orphanRemoval = true)
	@JsonBackReference
	protected List<StoreProduct> storeProducts;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "store", orphanRemoval = true)
	protected List<StoreHistory> history;

	public List<StoreProduct> getStoreProducts() {
		return storeProducts;
	}

	public boolean addStoreProduct(StoreProduct storeProduct) {
		if(storeProducts == null)
			storeProducts = new ArrayList<>();
		return storeProducts.add(storeProduct);
	}

	public boolean addCollaborator(StoreOwner storeOwner) {
		if(collaborators == null)
			collaborators = new HashSet<>();
		return collaborators.add(storeOwner);
	}

	public boolean removeCollaborator(StoreOwner storeOwner) {
		return collaborators.remove(storeOwner);
	}

	public boolean setStoreProducts(List<StoreProduct> storeProducts) {
		this.storeProducts = storeProducts;
		return true;
	}

	public StoreOwner getStoreOwner() {
		return storeOwner;
	}

	public void setStoreOwner(StoreOwner storeOwner) {
		this.storeOwner = storeOwner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StoreStatus getStatus() {
		return status;
	}

	public void setStatus(StoreStatus status) {
		this.status = status;
	}

	public Set<StoreOwner> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(Set<StoreOwner> collaborators) {
		this.collaborators = collaborators;
	}

	public List<StoreHistory> getHistory() {
		return history;
	}

	public void setHistory(List<StoreHistory> history) {
		this.history = history;
	}
}
