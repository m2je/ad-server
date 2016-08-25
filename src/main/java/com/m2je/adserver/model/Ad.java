package com.m2je.adserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;

@Table(name="ad")
@Entity
public class Ad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845046400389935354L;
	private String partnerId;
	private Integer duration;
	private String addContent;
	private Date adDate;
	@Id
	@Column(name="partner_id")
	@JsonProperty("partner_id")
	@NotBlank
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	@NotNull
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	@NotBlank
	@Column(name="ad_content")
	@JsonProperty("content")
	public String getAddContent() {
		return addContent;
	}
	public void setAddContent(String addContent) {
		this.addContent = addContent;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ad_date")
	@JsonIgnore
	public Date getAdDate() {
		return adDate;
	}
	public void setAdDate(Date adDate) {
		this.adDate = adDate;
	}
	
	
	
}
