package app.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class UserCrawled {

	@EmbeddedId
	private UserCrawledKey id;

	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("crawled_id")
	@JoinColumn(name = "crawled_id")
	private Crawled crawled;

	@Column(name = "viewed", length = 1, columnDefinition = "TINYINT")
	private Boolean viewed;

	@Column(name = "favourite", length = 1, columnDefinition = "TINYINT")
	private Boolean favourite;

	@Column(name = "compare", length = 1, columnDefinition = "TINYINT")
	private Boolean compare;

	public UserCrawled() {
		// TODO Auto-generated constructor stub
	}

	public UserCrawled(User user, Crawled crawled, Boolean viewed, Boolean favourite, Boolean compare) {
		super();
		this.user = user;
		this.crawled = crawled;
		this.viewed = viewed;
		this.favourite = favourite;
		this.compare = compare;
	}

	public UserCrawledKey getId() {
		return id;
	}

	public void setId(UserCrawledKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Crawled getCrawled() {
		return crawled;
	}

	public void setCrawled(Crawled crawled) {
		this.crawled = crawled;
	}

	public Boolean getViewed() {
		return viewed;
	}

	public void setViewed(Boolean viewed) {
		this.viewed = viewed;
	}

	public Boolean getFavourite() {
		return favourite;
	}

	public void setFavourite(Boolean favourite) {
		this.favourite = favourite;
	}

	public Boolean getCompare() {
		return compare;
	}

	public void setCompare(Boolean compare) {
		this.compare = compare;
	}

}
