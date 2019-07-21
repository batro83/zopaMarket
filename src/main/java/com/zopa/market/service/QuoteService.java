package com.zopa.market.service;

import java.util.List;

import com.zopa.market.beans.ZopaMarket;

/**
 * Calculate quote service
 * @author roger
 *
 */
public interface QuoteService {
	
	public void findQuote(List<ZopaMarket> marketList, final double loanAmount);
	
	
}
