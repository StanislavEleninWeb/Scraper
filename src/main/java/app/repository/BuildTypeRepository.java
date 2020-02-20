package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entity.BuildType;

@Repository
public interface BuildTypeRepository extends JpaRepository<BuildType, Integer> {

	@Query("SELECT b FROM BuildType b WHERE LOWER(b.title) LIKE ?1 OR LOWER(b.keywords) LIKE ?1")
	BuildType findBuildTypeByKeywords(String string);

}
