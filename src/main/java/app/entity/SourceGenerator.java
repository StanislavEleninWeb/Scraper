package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "source_generator")
public class SourceGenerator {

	@Id
	@Column(name = "source_id", nullable = false)
	private int id;

	@NotBlank
	@Size(min = 2)
	private String type;

	@NotBlank
	private String regex;

	@NotBlank
	private String generator;

	@OneToOne(mappedBy = "sourceGenerator", fetch = FetchType.LAZY, optional = false)
	private Source source;

	public SourceGenerator() {
		// TODO Auto-generated constructor stub
	}

	public SourceGenerator(@NotBlank @Size(min = 2) String type, @NotBlank String regex, @NotBlank String generator) {
		super();
		this.type = type;
		this.regex = regex;
		this.generator = generator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "SourceGenerator [id=" + id + ", type=" + type + ", regex=" + regex + ", generator=" + generator
				+ ", source=" + source + "]";
	}

}
