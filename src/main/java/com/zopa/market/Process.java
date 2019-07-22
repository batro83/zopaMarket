package com.zopa.market;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zopa.market.beans.ResponseQuote;
import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.ParserMarketService;
import com.zopa.market.service.QuoteService;
import com.zopa.market.service.impl.ParserMarketServiceImpl;
import com.zopa.market.service.impl.QuoteServiceImpl;
import com.zopa.market.utils.Constants;

public class Process {
	
	private static final Logger logger = LogManager.getLogger(Process.class);
	private DecimalFormat formatterNoDecimal;
	private DecimalFormat formatterOneDecimal;
	private DecimalFormat formatterTwoDecimal;	
	
	
	public Process() {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');		
		formatterNoDecimal = new DecimalFormat("#", dfs);
		formatterOneDecimal = new DecimalFormat("####,####.0", dfs);
		formatterTwoDecimal = new DecimalFormat("####,####.00", dfs);
	}
		
	
	public ResponseQuote init(File pathmarket, double loanAmount) throws Exception {
		if(loanAmount % Constants.LOAN_INCREMENT != 0 || loanAmount<Constants.MIN_LOAN_AMOUNT || loanAmount>Constants.MAX_LOAN_AMOUNT) {
			logger.error("A quote may be requested in any £100 increment between £{} and £{} inclusive", Constants.MIN_LOAN_AMOUNT, Constants.MAX_LOAN_AMOUNT);
			return null;
		}
								
		ParserMarketService parserMarketService = new ParserMarketServiceImpl();
		List<ZopaMarket> marketList = parserMarketService.parser(pathmarket);
		
		return calculateQuote(marketList, loanAmount);
	}
	
	
	private ResponseQuote calculateQuote(List<ZopaMarket> marketList, double loanAmount) {			
		QuoteService quoteService = new QuoteServiceImpl();				
		ResponseQuote response = new ResponseQuote(loanAmount);
		double totalMarket = quoteService.checkTotalMarketOffers(marketList);
		
		if(loanAmount>totalMarket) {
			logger.error("The market does not have enough offers to fulfil the request.");
			
		} else{			
			List<ZopaMarket> lendersList = quoteService.getLendersFromMarket(marketList, loanAmount);	
			quoteService.calculateMonthlyRepayments(quoteService.calculateAverageInterestRate(lendersList, response), loanAmount, response);	
			printQuoteResult(response);
		}
		
		return response;		
	}
	
	
	private void printQuoteResult(ResponseQuote response) {
		logger.info("Request amount: £{}", formatterNoDecimal.format(response.getRequestAmount()));
		logger.info("Annual Interest Rate: {}%", formatterOneDecimal.format(response.getAnualInterestRate() * 100));
		logger.info("Monthly repayment: £{}", formatterTwoDecimal.format(response.getMonthlyRepayment()));
		logger.info("Total repayment: £{}", formatterTwoDecimal.format(response.getTotalRepayment()));
	}

}
