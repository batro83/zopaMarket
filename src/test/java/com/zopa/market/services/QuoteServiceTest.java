package com.zopa.market.services;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zopa.market.UnitTestImportFileAbstract;
import com.zopa.market.beans.ResponseQuote;
import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.ParserMarketService;
import com.zopa.market.service.QuoteService;
import com.zopa.market.service.impl.ParserMarketServiceImpl;
import com.zopa.market.service.impl.QuoteServiceImpl;

public class QuoteServiceTest extends UnitTestImportFileAbstract{
		

	@Test
    public void getLendersFromMarketTestSuccess() throws Exception{  		
		QuoteService quoteService = new QuoteServiceImpl();		
		
		double loanAmount = 1000d;
		List<ZopaMarket> list = quoteService.getLendersFromMarket(getMarketList(), loanAmount);		
		Assert.assertEquals(2, list.size());
		
		loanAmount = 1500d;
		list = quoteService.getLendersFromMarket(getMarketList(), loanAmount);		
		Assert.assertEquals(5, list.size());
		
		loanAmount = 2000d;
		list = quoteService.getLendersFromMarket(getMarketList(), loanAmount);		
		Assert.assertEquals(6, list.size());
    }	
	
	@Test
    public void calculateAverageInterestRateTestSuccess() throws Exception{  		
		QuoteService quoteService = new QuoteServiceImpl();			
		double avg = quoteService.calculateAverageInterestRate(getMarketList(), new ResponseQuote());		
		Assert.assertEquals(0.07785714285714285d, avg, 0);
    }
	
	
	@Test
    public void checkTotalMarketOffersTestSuccess() throws Exception{  		
		QuoteService quoteService = new QuoteServiceImpl();			
		double avg = quoteService.checkTotalMarketOffers(getMarketList());
		
		Assert.assertEquals(2330d, avg, 0);
    }
	
		
	private List<ZopaMarket> getMarketList() throws Exception {
		File file = getResourceFile("Market/market.csv");
		
		ParserMarketService parserMarketService = new ParserMarketServiceImpl();
		return parserMarketService.parser(file);
	}

}
