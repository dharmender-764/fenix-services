package com.fenix.ecom.returns.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FenixConfig {
	
	@Value("${fenix.return-fee-url}")
	private String returnFeeUrl;

	public String getReturnFeeUrl() {
		return returnFeeUrl;
	}

	public void setReturnFeeUrl(String returnFeeUrl) {
		this.returnFeeUrl = returnFeeUrl;
	}
	
}
