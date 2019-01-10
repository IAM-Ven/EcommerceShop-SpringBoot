package io.github.thang86.common;


import io.github.thang86.entities.Store;
import io.github.thang86.entities.StoreProduct;

import java.util.List;

/**
*
*  SearchResult.java
* 
*  Version 1.0
*
*  Copyright
*
*  Modification Logs:
*  DATE		     AUTHOR		 DESCRIPTION
*  -------------------------------------
*  2018-12-15    ThangTX     Create
*
*/

public class SearchResult {
	private String query;
	private List<StoreProduct> storeProducts;
	private List<Store> stores;

	public SearchResult(String query, List<StoreProduct> storeProducts, List<Store> stores) {
		this.query = query;
		this.storeProducts = storeProducts;
		this.stores = stores;
	}

	public SearchResult() {
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<StoreProduct> getStoreProducts() {
		return storeProducts;
	}

	public void setStoreProducts(List<StoreProduct> storeProducts) {
		this.storeProducts = storeProducts;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}
}
