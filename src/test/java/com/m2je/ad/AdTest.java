package com.m2je.ad;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.m2je.adserver.model.Ad;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-api.xml")
public class AdTest {


	RestTemplate template ;
	
	@Resource
	protected List<HttpMessageConverter<?>> messageConverters;
	
	@Value("${serverAddress}")
	protected String serverAddress;

	
	@Before
	public void init(){
		if(template==null){
			template = new RestTemplate();
			template.setMessageConverters(messageConverters);
		}
	}
	
	@Test
	public void testCreate(){
		Ad ad=new Ad();
		ad.setAddContent("Test content");
		ad.setPartnerId(String.valueOf(System.currentTimeMillis()));
		ad.setDuration(10);
		Ad ad2= template.postForEntity(serverAddress+"/ad", ad, Ad.class).getBody();
		org.junit.Assert.assertNotNull(ad2);
		org.junit.Assert.assertEquals(ad.getPartnerId(), ad2.getPartnerId());
		org.junit.Assert.assertEquals(ad.getDuration(), ad2.getDuration());
		org.junit.Assert.assertEquals(ad.getAddContent(), ad2.getAddContent());
	}
	
	@Test
	public void testGet(){
		Ad ad=new Ad();
		ad.setAddContent("Test content");
		ad.setPartnerId(String.valueOf(System.currentTimeMillis()));
		ad.setDuration(10);
		template.postForEntity(serverAddress+"/ad", ad, Ad.class).getBody();
		Ad ad2= template.getForEntity(serverAddress+"/ad/"+ad.getPartnerId(),  Ad.class).getBody();
		org.junit.Assert.assertNotNull(ad2);
		org.junit.Assert.assertEquals(ad.getPartnerId(), ad2.getPartnerId());
		org.junit.Assert.assertEquals(ad.getDuration(), ad2.getDuration());
		org.junit.Assert.assertEquals(ad.getAddContent(), ad2.getAddContent());
	}
	
	@Test
	public void testList(){
		Ad ad=new Ad();
		ad.setAddContent("Test content");
		ad.setPartnerId(String.valueOf(System.currentTimeMillis()));
		ad.setDuration(10);
		ad= template.postForEntity(serverAddress+"/ad", ad, Ad.class).getBody();
		
		AdList result=template.getForEntity(serverAddress+"/ad?partner_id="+ad.getPartnerId(), AdList.class).getBody();
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size()>0);
	}
}
