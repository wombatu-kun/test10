package wombatukun.tests.test10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data @NoArgsConstructor @AllArgsConstructor
public class RequestDto {

	@NotBlank(message = "Value is required")
	@Size(min=2, max=50, message="Value must be 2-50 characters long")
	private String value;

	@Min(value = 1, message = "Page number must be greater than 0")
	private Integer page;

	@Min(value = 1, message = "Page size must be greater than 1")
	@Max(value = 100, message = "Page size must be lower than or equal to 100")
	private Integer pageSize;

}
