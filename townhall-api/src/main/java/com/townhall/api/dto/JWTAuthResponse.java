package com.townhall.api.dto;

import java.util.ArrayList;
import java.util.List;

public class JWTAuthResponse {
	
	private String accessToken;
	private String tokenType = "Bearer";
	private List<String> roles = new ArrayList<String>();

	public JWTAuthResponse(String accessToken, List<String> roles) {
		this.accessToken = accessToken;
		this.roles = roles;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
}
