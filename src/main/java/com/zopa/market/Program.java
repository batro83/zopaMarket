package com.zopa.market;

import java.io.IOException;
import java.util.List;

import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.ParserMarketService;
import com.zopa.market.service.QuoteService;

public class Program {

	public static void main(String[] args) {

		if(args.length>2 || args.length<2) {
			System.out.println("2 args are required.");
		}
				
		System.out.println("Request amount: "+ args[1]);
				
		ParserMarketService parserMarketService = new ParserMarketService();
		QuoteService quoteService = new QuoteService();
		try {
			List<ZopaMarket> marketList = parserMarketService.parser(args[0]);
			quoteService.findQuote(marketList, Double.parseDouble(args[1]));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
