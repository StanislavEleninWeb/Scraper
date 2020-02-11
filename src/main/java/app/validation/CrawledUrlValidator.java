package app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import app.service.CrawledService;

public class CrawledUrlValidator implements ConstraintValidator<CrawledUrl, String> {

	@Autowired
	private CrawledService crawledService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !crawledService.isCrawledUrlAlreadySaved(value);
	}

}
