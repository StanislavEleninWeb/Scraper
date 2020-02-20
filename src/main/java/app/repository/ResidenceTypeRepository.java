package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entity.ResidenceType;

@Repository
public interface ResidenceTypeRepository extends JpaRepository<ResidenceType, Integer> {

	@Query("SELECT r FROM ResidenceType r WHERE LOWER(r.title) LIKE ?1 OR LOWER(r.keywords) LIKE ?1")
	ResidenceType findResidenceTypeByKeywords(String string);

}
