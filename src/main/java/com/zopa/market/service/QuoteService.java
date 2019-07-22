package com.zopa.market.service;

import java.util.List;

import com.zopa.market.beans.ResponseQuote;
import com.zopa.market.beans.ZopaMarket;

/**
 * Calculate quote service
 * @author roger
 *
 */
public interface QuoteService {
	
//	public ResponseQuote findQuote(List<ZopaMarket> marketList, final double loanAmount);
	public List<ZopaMarket> getLendersFromMarket(List<ZopaMarket> marketList, double loanAmount);
	public double calculateAverageInterestRate(List<ZopaMarket> marketList, ResponseQuote response);
	public double checkTotalMarketOffers(List<ZopaMarket> marketList);
	public double calculateMonthlyRepayments(double averageInterestRate, double loanAmount, ResponseQuote response);
	
	
}
