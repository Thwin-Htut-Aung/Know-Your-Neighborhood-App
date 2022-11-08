package com.lithan.onlineproduct.googlefacebook.login.service;

import java.util.List;

import com.lithan.onlineproduct.googlefacebook.login.dao.Store;

public interface StoreService {

	List<Store> viewStores();
	List<Store> searchStores(String keyword);
}
