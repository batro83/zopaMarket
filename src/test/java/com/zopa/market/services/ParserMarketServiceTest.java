package com.zopa.market.services;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zopa.market.UnitTestImportFileAbstract;
import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.ParserMarketService;
import com.zopa.market.service.impl.ParserMarketServiceImpl;


public class ParserMarketServiceTest extends UnitTestImportFileAbstract{

	
	
	@Test
    public void parserMarketCsvSuccess() throws Exception{  
		File file = getResourceFile("Market/market.csv");
		
		ParserMarketService parserMarketService = new ParserMarketServiceImpl();
		List<ZopaMarket> list = parserMarketService.parser(file);
		
		Assert.assertEquals(7, list.size());	
    	
    }
	
	@Test
    public void parserMarketCsvError(){  
		File file = getResourceFile("Market/marketError.csv");
		
		ParserMarketService parserMarketService = new ParserMarketServiceImpl();
		List<ZopaMarket> list = null;
		try {
			list = parserMarketService.parser(file);
		} catch (Exception e) {
		}
		
		Assert.assertNull(list);
		
    }
}
