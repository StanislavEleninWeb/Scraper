package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserCrawled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public String toString() {
		return "UserCrawled [id=" + id + ", user=" + user + ", crawled=" + crawled + ", viewed=" + viewed
				+ ", favourite=" + favourite + ", compare=" + compare + "]";
	}

}
