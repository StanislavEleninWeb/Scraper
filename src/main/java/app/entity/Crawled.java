package app.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "crawled")
public class Crawled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank
	@URL
//	@CrawledUrl // Not working autowired CrawledService in CrawledUrlValidator
	@Column(name = "url")
	private String url;

	@Column(name = "created_at", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_id")
	private Source source;

	@OneToOne(mappedBy = "crawled", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private CrawledInfo crawledInfo;

	@OneToOne(mappedBy = "crawled", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private CrawledRating crawledRating;

	@OneToMany(mappedBy = "crawled", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy
	private Set<CrawledImage> crawledImages;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "crawled_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
	private Set<Contact> contacts;

	@OneToMany(mappedBy = "crawled", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserCrawled> userCrawled;

	public Crawled() {
		// TODO Auto-generated constructor stub
	}

	public Crawled(@NotBlank @URL String url) {
		super();
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public CrawledInfo getCrawledInfo() {
		return crawledInfo;
	}

	public void setCrawledInfo(CrawledInfo crawledInfo) {
		this.crawledInfo = crawledInfo;
	}

	public CrawledRating getCrawledRating() {
		return crawledRating;
	}

	public void setCrawledRating(CrawledRating crawledRating) {
		this.crawledRating = crawledRating;
	}

	public Set<CrawledImage> getCrawledImages() {
		return crawledImages;
	}

	public void setCrawledImages(Set<CrawledImage> crawledImages) {
		this.crawledImages = crawledImages;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<UserCrawled> getUserCrawled() {
		return userCrawled;
	}

	public void setUserCrawled(Set<UserCrawled> userCrawled) {
		this.userCrawled = userCrawled;
	}

	@Override
	public String toString() {
		return "Crawled [id=" + id + ", url=" + url + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
