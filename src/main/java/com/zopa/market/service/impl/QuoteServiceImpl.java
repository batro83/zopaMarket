package com.zopa.market.service.impl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.QuoteService;
import com.zopa.market.utils.Constants;

public class QuoteServiceImpl implements QuoteService{
	private static final Logger logger = LogManager.getLogger(QuoteServiceImpl.class);
	private DecimalFormat formatterNoDecimal;
	private DecimalFormat formatterOneDecimal;
	private DecimalFormat formatterTwoDecimal;	
	
	
	public QuoteServiceImpl() {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator('.');		
		formatterNoDecimal = new DecimalFormat("#", otherSymbols);
		formatterOneDecimal = new DecimalFormat("####,####.0", otherSymbols);
		formatterTwoDecimal = new DecimalFormat("####,####.##", otherSymbols);
	}

	@Override
	public void findQuote(List<ZopaMarket> marketList, final double loanAmount){
		logger.info("Request amount: £{}", formatterNoDecimal.format(loanAmount));
		double totalMarket = checkTotalMarketOffers(marketList);
		
		if(loanAmount>totalMarket) {
			logger.error("The market does not have enough offers to fulfil the request.");
		} else{
			List<ZopaMarket> lendersList = getLendersFromMarket(marketList, loanAmount);	
			calculateMonthlyRepayments(calculateAverageInterestRate(lendersList), loanAmount);	
		}	
	}
	
	
	/**
	 * Return the lowest rate lenders to covert the loan amount
	 * @param marketList
	 * @param loanAmount
	 * @return
	 */
	private List<ZopaMarket> getLendersFromMarket(List<ZopaMarket> marketList, double loanAmount) {
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
	 * @return
	 */
	private double calculateAverageInterestRate(List<ZopaMarket> marketList) {
		OptionalDouble avg = marketList.stream().mapToDouble(ZopaMarket::getRate).average();
		logger.info("Annual Interest Rate: {}%", formatterOneDecimal.format(avg.getAsDouble() * 100));
		return avg.getAsDouble();		
	}


	/**
	 * Calculate max available offers in the csv market
	 * @param marketList
	 * @return
	 */
	private double checkTotalMarketOffers(List<ZopaMarket> marketList) {
		return marketList.stream().mapToDouble(ZopaMarket::getAvailable).sum();
	}
	
	/**
	 * Calculate Monthly repayments 
	 * @param averageInterestRate
	 * @param loanAmount
	 * @return
	 */
	private double calculateMonthlyRepayments(double averageInterestRate, double loanAmount) {				
		double monthlyRate = averageInterestRate / 12.0;		
		double monthlyPayment = (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -Constants.LOANS_MONTH));		
		logger.info("Monthly repayment: £{}", formatterTwoDecimal.format(monthlyPayment));
		logger.info("Total repayment: £{}", formatterTwoDecimal.format(monthlyPayment * Constants.LOANS_MONTH));
		return monthlyPayment;
	}	
}