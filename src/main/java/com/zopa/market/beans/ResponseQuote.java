package com.zopa.market.beans;

public class ResponseQuote {
	
	private double requestAmount;
	private double anualInterestRate;
	private double monthlyRepayment;
	private double totalRepayment;
	
	public ResponseQuote() {
		super();
	}
	
	public ResponseQuote(double requestAmount) {
		super();
		this.requestAmount = requestAmount;
	}
	
	public double getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(double requestAmount) {
		this.requestAmount = requestAmount;
	}
	public double getAnualInterestRate() {
		return anualInterestRate;
	}
	public void setAnualInterestRate(double anualInterestRate) {
		this.anualInterestRate = anualInterestRate;
	}
	public double getMonthlyRepayment() {
		return monthlyRepayment;
	}
	public void setMonthlyRepayment(double monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}
	public double getTotalRepayment() {
		return totalRepayment;
	}
	public void setTotalRepayment(double totalRepayment) {
		this.totalRepayment = totalRepayment;
	}
	

}
