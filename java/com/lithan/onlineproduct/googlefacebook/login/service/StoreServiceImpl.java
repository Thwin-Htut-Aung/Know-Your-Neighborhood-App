package com.lithan.onlineproduct.googlefacebook.login.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lithan.onlineproduct.googlefacebook.login.dao.Store;
import com.lithan.onlineproduct.googlefacebook.login.repository.StoreRepository;


@Service
@Transactional
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreRepository storeRepo;
	
	@Override
	public List<Store> viewStores() {
		return storeRepo.findAll();
	}

	@Override
	public List<Store> searchStores(String keyword) {
	return storeRepo.searchByKey(keyword);
	}

}
