package com.spring.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "tripAdvisor")
public class TripAdvisor {

	@Id
	@Column(name = "locationId", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", table = "SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "TRIPADVISOR_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Integer locationId;
	
	@Column(name = "name", length = 45)
	private String name;
	
	@Column(name = "address", length = 45)
	private String address;
	
	@Column(name = "latitudeAndLongitude", length = 45)
	private Double latitudeAndLongitude;
	
	@Column(name = "readReview", length = 45)
	private String readReview;
	
	@Column(name = "writeReview", length = 45)
	private String writeReview;
	
	@Column(name = "overAllRating", length = 45)
	private String overAllRating;
	
	@Column(name = "ranking", length = 45)
	private String ranking;
	
	@Column(name = "subrating", length = 45)
	private String subrating;
	
	@Column(name = "awards", length = 45)
	private String awards;
	
	@Column(name = "numbersOfRating", length = 45)
	private String numbersOfRating;
	
	@Column(name = "ratingBubblesImage", length = 45)
	private String ratingBubblesImage;
	
	@Column(name = "priceLevelSymbol", length = 45)
	private String priceLevelSymbol;
	
	@Column(name = "accomodationCategory", length = 45)
	private String accomodationCategory;
	
	@Column(name = "restaurantCusines", length = 45)
	private String restaurantCusines;

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitudeAndLongitude() {
		return latitudeAndLongitude;
	}

	public void setLatitudeAndLongitude(Double latitudeAndLongitude) {
		this.latitudeAndLongitude = latitudeAndLongitude;
	}

	public String getReadReview() {
		return readReview;
	}

	public void setReadReview(String readReview) {
		this.readReview = readReview;
	}

	public String getWriteReview() {
		return writeReview;
	}

	public void setWriteReview(String writeReview) {
		this.writeReview = writeReview;
	}

	public String getOverAllRating() {
		return overAllRating;
	}

	public void setOverAllRating(String overAllRating) {
		this.overAllRating = overAllRating;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getSubrating() {
		return subrating;
	}

	public void setSubrating(String subrating) {
		this.subrating = subrating;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getNumbersOfRating() {
		return numbersOfRating;
	}

	public void setNumbersOfRating(String numbersOfRating) {
		this.numbersOfRating = numbersOfRating;
	}

	public String getRatingBubblesImage() {
		return ratingBubblesImage;
	}

	public void setRatingBubblesImage(String ratingBubblesImage) {
		this.ratingBubblesImage = ratingBubblesImage;
	}

	public String getPriceLevelSymbol() {
		return priceLevelSymbol;
	}

	public void setPriceLevelSymbol(String priceLevelSymbol) {
		this.priceLevelSymbol = priceLevelSymbol;
	}

	public String getAccomodationCategory() {
		return accomodationCategory;
	}

	public void setAccomodationCategory(String accomodationCategory) {
		this.accomodationCategory = accomodationCategory;
	}

	public String getRestaurantCusines() {
		return restaurantCusines;
	}

	public void setRestaurantCusines(String restaurantCusines) {
		this.restaurantCusines = restaurantCusines;
	}
	
	
}
