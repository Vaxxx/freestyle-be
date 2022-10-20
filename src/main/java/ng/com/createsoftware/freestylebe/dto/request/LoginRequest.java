
package ng.com.createsoftware.freestylebe.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
  private String stagename;

	@NotBlank
	private String password;

}
