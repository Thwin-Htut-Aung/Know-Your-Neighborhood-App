package com.lithan.onlineproduct.googlefacebook.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lithan.onlineproduct.googlefacebook.login.dao.Store;
import com.lithan.onlineproduct.googlefacebook.login.service.StoreService;


@RestController
@RequestMapping("/online")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	//view store page
	@GetMapping("/view-stores")
	public List<Store> viewStores(){
		List<Store> stores = storeService.viewStores();
		return stores;
	}
	
	//search for stores using a keyword
	@GetMapping("/storekey/{keyword}")
	public List<Store> searchByKey(@PathVariable String keyword){
		return storeService.searchStores(keyword);
	}
}
