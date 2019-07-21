package com.zopa.market.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zopa.market.beans.ZopaMarket;
import com.zopa.market.service.ParserMarketService;
import com.zopa.market.utils.Constants;

public class ParserMarketServiceImpl implements ParserMarketService {

	@Override
	public List<ZopaMarket> parser(String pathMarket) throws IOException {

		List<ZopaMarket> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(pathMarket))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(Constants.MARKET_CSV_SPLIT);
				records.add(new ZopaMarket(values[0], Double.valueOf(values[1]), Double.valueOf(values[2])));
			}
		}

		return records;
	}
}
