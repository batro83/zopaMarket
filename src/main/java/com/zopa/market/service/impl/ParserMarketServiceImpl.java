package com.zopa.market.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zopa.market.Process;
import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.ParserMarketService;
import com.zopa.market.utils.Constants;

public class ParserMarketServiceImpl implements ParserMarketService {
	
	private static final Logger logger = LogManager.getLogger(Process.class);

	@Override
	public List<ZopaMarket> parserCsvMarket(File pathMarket) throws Exception {

		List<ZopaMarket> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(pathMarket))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(Constants.MARKET_CSV_SPLIT);
				records.add(new ZopaMarket(values[0], Double.valueOf(values[1]), Double.valueOf(values[2])));
			}
		}catch (Exception e) {
			logger.error("Error parsing csv market. {}", e.getMessage());
			throw new Exception();
		}

		return records;
	}
}
