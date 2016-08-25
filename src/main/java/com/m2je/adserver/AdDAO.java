package com.m2je.adserver;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.m2je.adserver.model.Ad;

@Repository("adDAO")
public interface AdDAO extends JpaRepository<Ad,String>{
	
	
	@Query(value="SELECT Ad FROM Ad Ad WHERE Ad.partnerId=:partnerId")
	public Page<Ad> findByPartnerId(@Param("partnerId")String partnerId,Pageable pagination);
}
