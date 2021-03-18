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
@Table(name = "Continent")
public class Continent {

	@Id
	@Column(name = "mediaId", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", table = "SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CONTINENT_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Integer continentId;
	
	@Column(name = "continentName", length = 45)
	private String continentName;
	
	@Column(name = "ContinentGeojson", length = 45)
	private String continentGeojson;

	public Integer getContinentId() {
		return continentId;
	}

	public void setContinentId(Integer continentId) {
		this.continentId = continentId;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public String getContinentGeojson() {
		return continentGeojson;
	}

	public void setContinentGeojson(String continentGeojson) {
		this.continentGeojson = continentGeojson;
	}

	
	
}
