package app.repository.specs;

import java.math.BigDecimal;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import app.entity.BuildType;
import app.entity.Crawled;
import app.entity.ResidenceType;

@SuppressWarnings("serial")
public class CrawledSpecification extends AbstractSpecification<Crawled> {

	/**
	 * Specification for keyword
	 * 
	 * @param keyword
	 * @return Specification
	 */
	public static Specification<Crawled> getCrawledByKeyword(String keyword) {
		if (keyword == null || keyword.length() < 2)
			return null;
		return (root, query, builder) -> {
			Predicate predicate = builder.like(builder.lower(root.join("crawledInfo").get("title")),
					"%" + keyword.trim().toLowerCase() + "%");
			return predicate;
		};
	}

	/**
	 * Specification for minimum price
	 * 
	 * @param price
	 * @return
	 */
	public static Specification<Crawled> getCrawledByPriceMin(BigDecimal price) {
		if (price == null)
			return null;
		return (root, query, builder) -> {
			return builder.greaterThanOrEqualTo(root.join("crawledInfo").get("price"), price);
		};
	}

	/**
	 * Specification for maximum price
	 * 
	 * @param price
	 * @return
	 */
	public static Specification<Crawled> getCrawledByPriceMax(BigDecimal price) {
		if (price == null)
			return null;
		return (root, query, builder) -> {
			return builder.lessThanOrEqualTo(root.join("crawledInfo").get("price"), price);
		};
	}

	/**
	 * Specification for minimum price per square
	 * 
	 * @param price
	 * @return
	 */
	public static Specification<Crawled> getCrawledByPricePerSquareMin(BigDecimal price) {
		if (price == null)
			return null;
		return (root, query, builder) -> {
			return builder.greaterThanOrEqualTo(root.join("crawledInfo").get("pricePerSquare"), price);
		};
	}

	/**
	 * Specification for maximum price per square
	 * 
	 * @param price
	 * @return
	 */
	public static Specification<Crawled> getCrawledByPricePerSquareMax(BigDecimal price) {
		if (price == null)
			return null;
		return (root, query, builder) -> {
			return builder.lessThanOrEqualTo(root.join("crawledInfo").get("pricePerSquare"), price);
		};
	}

	/**
	 * Specification for minimum size
	 * 
	 * @param size
	 * @return
	 */
	public static Specification<Crawled> getCrawledBySizeMin(Short size) {
		if (size == null)
			return null;
		return (root, query, builder) -> {
			return builder.greaterThanOrEqualTo(root.join("crawledInfo").get("size"), size);
		};
	}

	/**
	 * Specification for maximum size
	 * 
	 * @param size
	 * @return
	 */
	public static Specification<Crawled> getCrawledBySizeMax(Short size) {
		if (size == null)
			return null;
		return (root, query, builder) -> {
			return builder.lessThanOrEqualTo(root.join("crawledInfo").get("size"), size);
		};
	}

	/**
	 * Specification for residence type
	 * 
	 * @param residenceType
	 * @return
	 */
	public static Specification<Crawled> getCrawledByResidenceType(ResidenceType residenceType) {
		if (residenceType == null)
			return null;
		return (root, query, builder) -> {
			return builder.equal(root.join("crawledInfo").get("type"), residenceType);
		};
	}

	/**
	 * Specification for build type
	 * 
	 * @param buildType
	 * @return
	 */
	public static Specification<Crawled> getCrawledByBuildType(BuildType buildType) {
		if (buildType == null)
			return null;
		return (root, query, builder) -> {
			return builder.equal(root.join("crawledInfo").get("buildType"), buildType);
		};
	}

	/**
	 * Combine all specifications if not null
	 * 
	 * @param keyword
	 * @param priceMin
	 * @param priceMax
	 * @param pricePerSquareMin
	 * @param pricePerSquareMax
	 * @param sizeMin
	 * @param sizeMax
	 * @param residenceType
	 * @param buildType
	 * @return
	 */
	public static Specification<Crawled> getCrawledBySearchCriteria(String keyword, BigDecimal priceMin,
			BigDecimal priceMax, BigDecimal pricePerSquareMin, BigDecimal pricePerSquareMax, Short sizeMin,
			Short sizeMax, ResidenceType residenceType, BuildType buildType) {
		return Specification.where(getCrawledByKeyword(keyword)).and(getCrawledByPriceMin(priceMin))
				.and(getCrawledByPriceMax(priceMax)).and(getCrawledByPricePerSquareMin(pricePerSquareMin))
				.and(getCrawledByPricePerSquareMax(pricePerSquareMax)).and(getCrawledBySizeMin(sizeMin))
				.and(getCrawledBySizeMax(sizeMax)).and(getCrawledByResidenceType(residenceType))
				.and(getCrawledByBuildType(buildType));
	}

}
