package com.spring.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "city")
public class City {
	
	@Id
	@Column(name = "cityId", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", table = "SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CITY_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Integer cityId;
	
	@Column(name = "cityName", length = 45)
	private String cityName;
	
	@Column(name = "cityGeojson", length = 45)
	private String cityGeojson;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityGeojson() {
		return cityGeojson;
	}

	public void setCityGeojson(String cityGeojson) {
		this.cityGeojson = cityGeojson;
	}
	
	

}
