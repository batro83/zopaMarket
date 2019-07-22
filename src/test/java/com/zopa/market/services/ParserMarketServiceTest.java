package com.zopa.market.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.ParserMarketService;
import com.zopa.market.service.impl.ParserMarketServiceImpl;


public class ParserMarketServiceTest {

	
	
	@Test
    public void parserMarketCsvSuccess() throws IOException{  
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("Market/market.csv").getFile());
		
		ParserMarketService parserMarketService = new ParserMarketServiceImpl();
		List<ZopaMarket> list = parserMarketService.parser(file);
		
		Assert.assertEquals(7, list.size());	
    	
    }
	
	@Test
    public void parserMarketCsvError() throws IOException{  
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("Market/marketError.csv").getFile());
		
		ParserMarketService parserMarketService = new ParserMarketServiceImpl();
		List<ZopaMarket> list = parserMarketService.parser(file);
		
		Assert.assertEquals(0, list.size());
		
    	
    }
}
