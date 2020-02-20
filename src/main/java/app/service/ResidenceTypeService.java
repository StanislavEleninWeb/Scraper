package app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.ResidenceType;
import app.repository.ResidenceTypeRepository;

@Service
@Transactional
public class ResidenceTypeService {

	@Autowired
	private ResidenceTypeRepository residenceTypeRepository;

	public List<ResidenceType> findAll() {
		return residenceTypeRepository.findAll();
	}

	public ResidenceType findById(int id) {
		return residenceTypeRepository.findById(id).get();
	}

	public ResidenceType getOne(int id) {
		return residenceTypeRepository.getOne(id);
	}

	public ResidenceType save(ResidenceType residenceType) {
		return residenceTypeRepository.save(residenceType);
	}

	public ResidenceType findResidenceTypeByKeywords(String string) {
		return residenceTypeRepository.findResidenceTypeByKeywords("%" + string.trim().toLowerCase() + "%");
	}
}
