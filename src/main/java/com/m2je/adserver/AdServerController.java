package com.m2je.adserver;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.m2je.adserver.model.Ad;

@Controller
public class AdServerController {

	@Autowired
	private AdService adService;
	
	@ResponseBody
	@RequestMapping(value="/ad",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Ad create(@Valid @RequestBody(required=true) Ad ad){
		return adService.create(ad);
	}
	
	@ResponseBody
	@RequestMapping(value="/ad",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Ad> list(@RequestParam(required=true,value="partner_id") String partnerId){
		return adService.list(partnerId);
	}
	
	@ResponseBody
	@RequestMapping(value="/ad/{partner_id}",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public Ad findByPartnerId(@PathVariable("partner_id") String partnerId){
		Ad ad=new Ad();
		ad.setPartnerId(partnerId);
		return adService.find(ad);
	}
}
