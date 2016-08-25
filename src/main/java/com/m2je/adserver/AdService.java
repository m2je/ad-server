package com.m2je.adserver;

import java.util.List;

import com.m2je.adserver.model.Ad;

public interface AdService {
	
	/**
	 * 
	 * @param ad
	 * @return
	 */
	public Ad create(Ad ad);
	/**
	 * 
	 * @param ad
	 * @return
	 */
	public Ad find(Ad ad);
	/**
	 * 
	 * @param partnerId
	 * @return
	 */
	public List<Ad> list(String partnerId); 
}
