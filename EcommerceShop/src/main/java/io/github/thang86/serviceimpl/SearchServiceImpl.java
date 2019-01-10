package io.github.thang86.serviceimpl;


import io.github.thang86.common.SearchResult;
import io.github.thang86.entities.Store;
import io.github.thang86.entities.StoreProduct;
import io.github.thang86.service.SearchService;
import org.apache.lucene.search.Query;
import org.hibernate.search.errors.EmptyQueryException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.min;
import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

/**
*  SearchServiceImpl.java
* 
*  Version 1.0
*
*  Copyright
*
*  Modification Logs:
*  DATE		     AUTHOR		 DESCRIPTION
*  -------------------------------------
*  2018-12-20    ThangTX     Create
*/

@Service
public class SearchServiceImpl implements SearchService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public SearchResult generalSearch(String queryString) {
		return new SearchResult(queryString, storeProductSearch(queryString, 500), storeSearch(queryString, 300));
	}

	@Override
	public SearchResult autoCompleteSearch(String queryString) {

			List<StoreProduct> storeProducts = storeProductSearch(queryString, 150);
			List<Store> stores = storeSearch(queryString, 150);

			//T(again this is a  temp fix for prototyping, TODO actual limit shouldn't get the data from the DB. (Fix this when query limiting TODO is done)
			return new SearchResult(queryString,
				storeProducts.subList(0, min(10, storeProducts.size())),
				stores.subList(0, min(10, stores.size()))
		);
	}

	@Override
	public List<StoreProduct> storeProductSearch(String queryString, Integer maxTimeLimit) {
		FullTextEntityManager fullTextEntityManager = getFullTextEntityManager(entityManager);

		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(StoreProduct.class).get();

		// query by keywords
		Query query;
		try {
			query = queryBuilder
					.keyword()
					.fuzzy()
					.withThreshold(0.4f) //default = 0.5
					.withPrefixLength(1) //At least first one to be correct.
					.onFields("name", "description", "product.name")
					.boostedTo(5)   //give above more weight
					.andField("product.brand.name")
					.andField("product.company.name")
					.andField("store.name")
					.matching(queryString)
					.createQuery();
		} catch (EmptyQueryException exception) {
			// return empty result if EmptyQuery occurred
			/* This exception occurs when users input is only consisting of stopwords (words ignored from search),
			   stopwords are ignored which leads to EmptyQueryException.
		    */
			return new ArrayList<>();
		}


		// wrap Lucene query in an Hibernate Query object
		FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, StoreProduct.class);

		jpaQuery.limitExecutionTimeTo(maxTimeLimit, TimeUnit.MILLISECONDS);

		//TODO (Set a limit for number of result per query, setMax isn't included in jpa, search for alternative.)

		// execute search and return results (sorted by relevance as default)
		List<StoreProduct> results = jpaQuery.getResultList();

		//TODO This doesn't solve the above TODO, it is just for the prototyping, (actual limit shouldn't get the data from the DB)
		return results.subList(0, min(results.size(),50));
	}

	@Override
	public List<Store> storeSearch(String queryString, Integer maxTimeLimit) {
		FullTextEntityManager fullTextEntityManager = getFullTextEntityManager(entityManager);

		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Store.class).get();

		// query by keywords
		Query query;
		try {
			query = queryBuilder
					.keyword()
					.fuzzy()
					//.withThreshold(0.8f) default = 0.5
					.withPrefixLength(1)
					.onFields("name")
					.matching(queryString)
					.createQuery();
		} catch (EmptyQueryException exception) {
			// return empty result if EmptyQuery occurred
				/* This exception occurs when users input is only consisting of stopwords (words ignored from search),
				   stopwords are ignored which leads to EmptyQueryException.
			    */
			return new ArrayList<>();
		}

		// wrap Lucene query in an Hibernate Query object
		FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Store.class);

		jpaQuery.limitExecutionTimeTo(maxTimeLimit, TimeUnit.MILLISECONDS);

		//TODO Filter unaccepted stores.

		//TODO (Set a limit for number of result per query, setMax isn't included in jpa, search for alternative.)

		// execute search and return results (sorted by relevance as default)
		List results = jpaQuery.getResultList();

		//TODO This doesn't solve the above TODO, it is just for the prototyping, (actual limit shouldn't get the data from the DB)
		return results.subList(0, min(results.size(),50));	}
}
