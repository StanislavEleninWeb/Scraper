package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import app.enumerated.RequestTypeEnum;

@Entity
@Table(name = "source_generator")
public class SourceGenerator {

	@Id
	@Column(name = "source_id", nullable = false)
	private int id;

	@Enumerated(EnumType.STRING)
	private RequestTypeEnum type;

	@NotBlank
	@URL
	private String url;

	@NotBlank
	@Column(name = "link_regex", nullable = false)
	private String linkRegex;

	@NotBlank
	@Column(name = "content_analyzer", nullable = false)
	private String contentAnalyzer;

	@NotBlank
	@Column(name = "bean", nullable = false)
	private String bean;

	@OneToOne(mappedBy = "sourceGenerator", fetch = FetchType.LAZY, optional = false)
	private Source source;

	public SourceGenerator() {
		// TODO Auto-generated constructor stub
	}

	public SourceGenerator(RequestTypeEnum type, @NotBlank @URL String url, @NotBlank String linkRegex,
			@NotBlank String contentAnalyzer, @NotBlank String bean) {
		super();
		this.type = type;
		this.url = url;
		this.linkRegex = linkRegex;
		this.contentAnalyzer = contentAnalyzer;
		this.bean = bean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RequestTypeEnum getType() {
		return type;
	}

	public void setType(RequestTypeEnum type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLinkRegex() {
		return linkRegex;
	}

	public void setLinkRegex(String linkRegex) {
		this.linkRegex = linkRegex;
	}

	public String getContentAnalyzer() {
		return contentAnalyzer;
	}

	public void setContentAnalyzer(String contentAnalyzer) {
		this.contentAnalyzer = contentAnalyzer;
	}

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "SourceGenerator [id=" + id + ", type=" + type + ", url=" + url + ", linkRegex=" + linkRegex
				+ ", contentAnalyzer=" + contentAnalyzer + "]";
	}

}
