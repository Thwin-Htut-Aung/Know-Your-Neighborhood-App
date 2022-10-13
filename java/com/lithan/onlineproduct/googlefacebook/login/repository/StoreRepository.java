package com.lithan.onlineproduct.googlefacebook.login.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lithan.onlineproduct.googlefacebook.login.dao.Store;


@Repository
public interface StoreRepository extends JpaRepository<Store, Integer>{

	@Query(value = "SELECT s FROM Store s WHERE storeName LIKE '%' || :keyword || '%' " +  
			"OR storeLocation LIKE '%' || :keyword || '%' OR storeProduct LIKE '%' || :keyword || '%'")
	public List<Store> searchByKey(@Param("keyword")String keyword);
}
