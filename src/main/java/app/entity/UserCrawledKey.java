package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class UserCrawledKey implements Serializable {

	@Column(name = "user_id")
	Long user_id;

	@Column(name = "crawled_id")
	Long crawledId;

	public UserCrawledKey() {
		// TODO Auto-generated constructor stub
	}

	public UserCrawledKey(Long user_id, Long crawledId) {
		super();
		this.user_id = user_id;
		this.crawledId = crawledId;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCrawledId() {
		return crawledId;
	}

	public void setCrawledId(Long crawledId) {
		this.crawledId = crawledId;
	}

	@Override
	public String toString() {
		return "UserCrawledKey [user_id=" + user_id + ", crawledId=" + crawledId + "]";
	}

}
