package app.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = CrawledUrlValidator.class)
public @interface CrawledUrl {

	public String message() default "There is already url with this address!";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};

}
