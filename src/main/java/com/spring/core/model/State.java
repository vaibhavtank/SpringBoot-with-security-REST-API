package com.spring.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "state")
public class State {

	@Id
	@Column(name = "stateId", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", table = "SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "STATE_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Integer stateId;
	
	@Column(name = "stateName", length = 45)
	private String stateName;
	
	@Column(name = "stateGeojson", length = 45)
	private String stateGeojson;

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateGeojson() {
		return stateGeojson;
	}

	public void setStateGeojson(String stateGeojson) {
		this.stateGeojson = stateGeojson;
	}
	
}
