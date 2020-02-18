package app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.BuildType;
import app.repository.BuildTypeRepository;

@Service
@Transactional
public class BuildTypeService {

	@Autowired
	private BuildTypeRepository buildTypeRepository;

	public List<BuildType> findAll() {
		return buildTypeRepository.findAll();
	}

	public BuildType findById(int id) {
		return buildTypeRepository.findById(id).get();
	}

	public BuildType getOne(int id) {
		return buildTypeRepository.getOne(id);
	}

	public BuildType save(BuildType buildType) {
		return buildTypeRepository.save(buildType);
	}

	public BuildType findFirstByTitleOrKeywords(String string) {
		return buildTypeRepository.findFirstByTitleOrKeywords(string, string);
	}

}
