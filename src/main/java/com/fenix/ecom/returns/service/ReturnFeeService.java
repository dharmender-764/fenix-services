package com.fenix.ecom.returns.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fenix.ecom.returns.configuration.FenixConfig;
import com.fenix.ecom.returns.data.ReturnFeeRequest;

@Service
public class ReturnFeeService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private FenixConfig fenixConfig;

	public BigDecimal getReturnFee(ReturnFeeRequest returnFeeRequest){
		BigDecimal returnFee;
		ReturnFeeRequest returnFeeResponse = restTemplate.postForObject(fenixConfig.getReturnFeeUrl(),returnFeeRequest,ReturnFeeRequest.class );
		if(null != returnFeeResponse.getReturnFee()){
			returnFee = new BigDecimal(returnFeeResponse.getReturnFee());
		}
		else{
			returnFee = null;
		}
		return returnFee;
	}

}
