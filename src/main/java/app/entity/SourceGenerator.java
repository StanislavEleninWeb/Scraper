package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "source_generator")
public class SourceGenerator {

	@Id
	@Column(name = "id")
	private int id;

	@NotBlank
	@Size(min = 2)
	private String type;

	private String generator;

	@OneToOne
	@MapsId
	private Source source;

	public SourceGenerator() {
		// TODO Auto-generated constructor stub
	}

	public SourceGenerator(@NotBlank @Size(min = 2) String type, String generator) {
		super();
		this.type = type;
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

}
