package com.spring.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "media")
public class Media {

	@Id
	@Column(name = "mediaId", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", table = "SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "MEDIA_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Integer mediaId;
	
	@Column(name = "mediaName", length = 45)
	private String mediaName;
	
	@Column(name = "mediaFile", length = 45)
	private String mediaFile;

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(String mediaFile) {
		this.mediaFile = mediaFile;
	}
	
}
