package com.zopa.market.beans;

public class ZopaMarket {
		
	private String lender;
	private Double rate;
	private Double available;
	
	
	public ZopaMarket(String lender, Double rate, Double available) {
		super();
		this.lender = lender;
		this.rate = rate;
		this.available = available;
	}
	
	
	public String getLender() {
		return lender;
	}
	public void setLender(String lender) {
		this.lender = lender;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getAvailable() {
		return available;
	}
	public void setAvailable(Double available) {
		this.available = available;
	}

}
