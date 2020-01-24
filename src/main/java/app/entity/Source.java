package app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "source")
public class Source {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "url")
	private String url;

	@Column(name = "active", length = 1, columnDefinition = "TINYINT")
	private boolean active;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "source", fetch = FetchType.LAZY)
	private List<Crawled> crawled;

	@OneToOne(mappedBy = "source", fetch = FetchType.LAZY)
	private SourceGenerator sourceGenerator;
	
	public Source() {
		// TODO Auto-generated constructor stub
	}

	public Source(String title, String description, String url, boolean active) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public List<Crawled> getCrawled() {
		return crawled;
	}

	public void setCrawled(List<Crawled> crawled) {
		this.crawled = crawled;
	}

	public SourceGenerator getSourceGenerator() {
		return sourceGenerator;
	}

	public void setSourceGenerator(SourceGenerator sourceGenerator) {
		this.sourceGenerator = sourceGenerator;
	}

}
