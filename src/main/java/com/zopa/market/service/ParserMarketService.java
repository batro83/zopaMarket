package com.zopa.market.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.zopa.market.beans.ZopaMarket;

/**
 * Parse market service
 * @author roger
 *
 */
public interface ParserMarketService {
	
	public List<ZopaMarket> parser(File pathmarket) throws IOException ;
	

}
