package io.github.thang86.controllers;


import io.github.thang86.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
*
*  SearchController.java
* 
*  Version 1.0
*
*  Copyright
*
*  Modification Logs:
*  DATE		     AUTHOR		 DESCRIPTION
*  -------------------------------------
*  2019-01-04    ThangTX     Create
*
*/

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "q", required = false) String queryString) {
		if(queryString == null || queryString.isEmpty())
			return new ModelAndView("search/index");
		else
			return new ModelAndView("search/result", "searchResult", searchService.generalSearch(queryString));
	}

}
