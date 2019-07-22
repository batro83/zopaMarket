package com.zopa.market.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;

import com.zopa.market.beans.ResponseQuote;
import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.QuoteService;
import com.zopa.market.utils.Constants;

public class QuoteServiceImpl implements QuoteService{
		
	
	/**
	 * Return the lowest lenders rate to covert the loan amount
	 * @param marketList
	 * @param loanAmount
	 * @return
	 */
	@Override
	public List<ZopaMarket> getLendersFromMarket(List<ZopaMarket> marketList, double loanAmount) {
		List<ZopaMarket> lendersList = new ArrayList<>();		
		double counterAmount = 0;
		
		while(counterAmount<loanAmount){
			ZopaMarket minLenderRate = marketList
				      .stream()
				      .min(Comparator.comparing(ZopaMarket::getRate))
				      .orElseThrow(NoSuchElementException::new);
			
			marketList.remove(minLenderRate);
			lendersList.add(minLenderRate);
			counterAmount += minLenderRate.getAvailable();
		}
		
		return lendersList;
	}


	/**
	 * Calculate average interest rate from lenders
	 * @param marketList
	 * @param response 
	 * @return
	 */
	@Override
	public double calculateAverageInterestRate(List<ZopaMarket> marketList, ResponseQuote response) {
		OptionalDouble avg = marketList.stream().mapToDouble(ZopaMarket::getRate).average();
		response.setAnualInterestRate(avg.getAsDouble());
		return avg.getAsDouble();		
	}


	/**
	 * Calculate max available offers in the csv market
	 * @param marketList
	 * @return
	 */
	@Override
	public double checkTotalMarketOffers(List<ZopaMarket> marketList) {
		return marketList.stream().mapToDouble(ZopaMarket::getAvailable).sum();
	}
	
	/**
	 * Calculate Monthly repayments 
	 * @param averageInterestRate
	 * @param loanAmount
	 * @param response 
	 * @return
	 */
	@Override
	public double calculateMonthlyRepayments(double averageInterestRate, double loanAmount, ResponseQuote response) {				
		double monthlyRate = averageInterestRate / 12.0;		
		double monthlyPayment = (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -Constants.LOANS_MONTH));		
		response.setMonthlyRepayment(monthlyPayment);
		response.setTotalRepayment(monthlyPayment * Constants.LOANS_MONTH);
		return monthlyPayment;
	}	
}
