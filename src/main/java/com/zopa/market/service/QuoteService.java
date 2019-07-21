package com.zopa.market.service;

import java.util.List;

import com.zopa.market.beans.ZopaMarket;

public class QuoteService {

	
	public void findQuote(List<ZopaMarket> marketList, double loanAmount){
		System.out.println("Request amount: "+ loanAmount);
		double totalMarket = chechTotalMarketOffers(marketList);
		
		if(loanAmount>totalMarket) {
			System.out.println("The market does not have enough offers to fulfil the request.");
		}
			
		
	}
	
	
	
	private double chechTotalMarketOffers(List<ZopaMarket> marketList) {
		double sum = marketList.stream().mapToDouble(ZopaMarket::getAvailable).sum();
		System.out.println(sum);
		return sum;
	}
	
	
}
