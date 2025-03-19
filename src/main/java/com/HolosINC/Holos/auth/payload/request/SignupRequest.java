package com.HolosINC.Holos.auth.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String authority;

	@NotBlank
	private String password;
	
	@NotBlank
	private String firstName;
}
