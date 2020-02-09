package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.BuildType;

@Repository
public interface BuildTypeRepository extends JpaRepository<BuildType, Integer> {

}
