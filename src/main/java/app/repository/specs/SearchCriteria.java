package app.repository.specs;

import app.enumerated.SearchOperation;

public class SearchCriteria {

	private String key;
	private Object value;
	private String reference;
	private SearchOperation operation;

	public SearchCriteria() {
		// TODO Auto-generated constructor stub
	}

	public SearchCriteria(String key, Object value, SearchOperation operation) {
		super();
		this.key = key;
		this.value = value;
		this.operation = operation;
	}

	public SearchCriteria(String key, Object value, String reference, SearchOperation operation) {
		super();
		this.key = key;
		this.value = value;
		this.reference = reference;
		this.operation = operation;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public SearchOperation getOperation() {
		return operation;
	}

	public void setOperation(SearchOperation operation) {
		this.operation = operation;
	}

}
