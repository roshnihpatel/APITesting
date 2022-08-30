package com.sparta.rp.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RatesPojo{

	@JsonProperty("date")
	private String date;

	@JsonProperty("success")
	private Boolean success;

	@JsonProperty("rates")
	private Rates rates;

	@JsonProperty("timestamp")
	private Integer timestamp;

	@JsonProperty("base")
	private String base;

	public String getDate(){
		return date;
	}

	public Boolean isSuccess(){
		return success;
	}

	public Rates getRates(){
		return rates;
	}

	public Integer getTimestamp(){
		return timestamp;
	}

	public String getBase(){
		return base;
	}

	@Override
 	public String toString(){
		return 
			"RatesPojo{" + 
			"date = '" + date + '\'' + 
			",success = '" + success + '\'' + 
			",rates = '" + rates + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			",base = '" + base + '\'' + 
			"}";
		}
}