package com.m2je.adserver;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.m2je.adserver.model.Ad;

@Service
public class AdServiceImpl implements AdService {

	@Autowired
	private AdDAO adDAO;
	
	@Override
	public Ad create(Ad ad) {
		Ad dbAd= find(ad);
		
		if(dbAd!=null){
			Calendar c=Calendar.getInstance();
			c.setTime(dbAd.getAdDate());
			c.add(Calendar.SECOND, dbAd.getDuration());
			if(c.getTimeInMillis()>System.currentTimeMillis()){
				throw new IllegalArgumentException("An active Ad for partner with id:"+ad.getPartnerId()+" already exists");
			}
		}
		return adDAO.save(ad);
	}

	@Override
	public Ad find(Ad ad) {
		Page<Ad>ads= adDAO.findByPartnerId(ad.getPartnerId(), new PageRequest(0, 1, new Sort(new Order(Direction.DESC,"adDate"))));
		return ads.getContent().size()>0?ads.getContent().get(0):null;
	}

	@Override
	public List<Ad> list(String partnerId) {
		return adDAO.findByPartnerId(partnerId, new PageRequest(0, 10, new Sort(new Order(Direction.DESC,"adDate")))).getContent();
	}

}
