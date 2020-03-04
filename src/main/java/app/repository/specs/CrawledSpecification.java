package app.repository.specs;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import app.entity.Crawled;

@SuppressWarnings("serial")
public class CrawledSpecification extends AbstractSpecification<Crawled> {

	public static Specification<Crawled> getCrawledByName(SearchCriteria criteria) {
		return (root, query, builder) -> {
			Predicate predicate = builder.like(builder.lower(root.get(criteria.getKey())),
					"%" + criteria.getValue().toString().toLowerCase() + "%");
			return predicate;
		};
	}

	public static Specification<Crawled> getCrawledByLessThenPrice(SearchCriteria criteria) {
		return (root, query, builder) -> {

//			Path<Integer> priceCriteria = root.join("crawledInfo").get("price");
//
//			return builder.lessThan(priceCriteria, price);
			return builder.lessThan(root.join("crawledInfo").get(criteria.getKey()),
					criteria.getValue().toString());
		};
	}

	public static Specification<Crawled> getCrawledByGreaterByPrice(SearchCriteria criteria) {
		return (root, query, builder) -> {
			return builder.greaterThanOrEqualTo(root.join("crawledInfo").get(criteria.getKey()),
					criteria.getValue().toString());
		};
	}
	
	public static Specification<Crawled> getCrawledByPrice(SearchCriteria priceMin, SearchCriteria priceMax){
		return Specification.where(getCrawledByGreaterByPrice(priceMin)).and(getCrawledByLessThenPrice(priceMax));
	}

}
