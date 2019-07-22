package com.zopa.market;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.ParserMarketService;
import com.zopa.market.service.QuoteService;
import com.zopa.market.service.impl.ParserMarketServiceImpl;
import com.zopa.market.service.impl.QuoteServiceImpl;
import com.zopa.market.utils.Constants;

public class Program {
	
	private static final Logger logger = LogManager.getLogger(Program.class);

	public static void main(String[] args) {

		if(args.length>2 || args.length<2) {
			logger.error("2 args are required. 1 Market path 2 loan amount");
		}
		
		try {
			double loanAmount = Double.parseDouble(args[1]);
			
			if(loanAmount % Constants.LOAN_INCREMENT != 0 || 
					loanAmount<Constants.MIN_LOAN_AMOUNT || 
					loanAmount>Constants.MAX_LOAN_AMOUNT) {
				logger.error("A quote may be requested in any £100 increment between £{} and £{} inclusive", Constants.MIN_LOAN_AMOUNT, Constants.MAX_LOAN_AMOUNT);
				return;
			}
									
			ParserMarketService parserMarketService = new ParserMarketServiceImpl();
			QuoteService quoteService = new QuoteServiceImpl();
		
			List<ZopaMarket> marketList = parserMarketService.parser(args[0]);
			quoteService.findQuote(marketList, Double.parseDouble(args[1]));

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
