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

}
