package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.ResidenceType;

@Repository
public interface ResidenceTypeRepository extends JpaRepository<ResidenceType, Integer> {

	ResidenceType findFirstByTitleOrKeywords(String string, String string2);

}
