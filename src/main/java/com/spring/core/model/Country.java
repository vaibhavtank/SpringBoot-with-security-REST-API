package com.spring.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "country")
public class Country {

	@Id
	@Column(name = "mediaId", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", table = "SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "COUNTRY_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Integer countryId;
	
	@Column(name = "countryName", length = 45)
	private String countryName;
	
	@Column(name = "countryGeojson", length = 45)
	private String countryGeojson;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryGeojson() {
		return countryGeojson;
	}

	public void setCountryGeojson(String countryGeojson) {
		this.countryGeojson = countryGeojson;
	}
	
	
}
