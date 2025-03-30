package org.flooc.combo.web.util;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class ExceptionUtils {

	private ExceptionUtils() {
	}

	public static String extract(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		StringBuilder sb = new StringBuilder();
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			allErrors.forEach(i -> {
				FieldError errors = (FieldError) i;
				sb.append(errors.getDefaultMessage());
			});
		}
		return sb.toString();
	}

}
