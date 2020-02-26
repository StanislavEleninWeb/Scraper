package app.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_criteria")
public class UserCriteria {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "primary", length = 1, columnDefinition = "TINYINT")
	private Boolean primary;

	@Column(name = "price_min")
	private BigDecimal priceMin;

	@Column(name = "price_max")
	private BigDecimal priceMax;

	@Column(name = "price_per_square_min")
	private BigDecimal pricePerSquareMin;

	@Column(name = "price_per_square_max")
	private BigDecimal pricePerSquareMax;

	@Column(name = "size_min")
	private Short sizeMin;

	@Column(name = "size_max")
	private Short sizeMax;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	private ResidenceType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "build_type")
	private BuildType buildType;

	@Column(name = "keywords")
	private String keywords;

	@Column(name = "notify_via_email", length = 1, columnDefinition = "TINYINT")
	private Boolean notifyViaEmail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public UserCriteria() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public BigDecimal getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(BigDecimal priceMin) {
		this.priceMin = priceMin;
	}

	public BigDecimal getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(BigDecimal priceMax) {
		this.priceMax = priceMax;
	}

	public BigDecimal getPricePerSquareMin() {
		return pricePerSquareMin;
	}

	public void setPricePerSquareMin(BigDecimal pricePerSquareMin) {
		this.pricePerSquareMin = pricePerSquareMin;
	}

	public BigDecimal getPricePerSquareMax() {
		return pricePerSquareMax;
	}

	public void setPricePerSquareMax(BigDecimal pricePerSquareMax) {
		this.pricePerSquareMax = pricePerSquareMax;
	}

	public Short getSizeMin() {
		return sizeMin;
	}

	public void setSizeMin(Short sizeMin) {
		this.sizeMin = sizeMin;
	}

	public Short getSizeMax() {
		return sizeMax;
	}

	public void setSizeMax(Short sizeMax) {
		this.sizeMax = sizeMax;
	}

	public ResidenceType getType() {
		return type;
	}

	public void setType(ResidenceType type) {
		this.type = type;
	}

	public BuildType getBuildType() {
		return buildType;
	}

	public void setBuildType(BuildType buildType) {
		this.buildType = buildType;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Boolean getNotifyViaEmail() {
		return notifyViaEmail;
	}

	public void setNotifyViaEmail(Boolean notifyViaEmail) {
		this.notifyViaEmail = notifyViaEmail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
