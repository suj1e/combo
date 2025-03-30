package org.flooc.combo.dataoperation.validate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class ValidateExecutor {

  private final List<Validator> VALIDATORS;

  public <T> void validate(T data) {
    Set<Validator> validators = VALIDATORS.stream()
        .filter(i -> i.supports(data.getClass()))
        .collect(Collectors.toSet());
    if (!CollectionUtils.isEmpty(validators)) {
      validators.forEach(i -> i.validate(data));
    }
  }

}
