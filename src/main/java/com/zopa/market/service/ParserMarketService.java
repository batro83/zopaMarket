package com.zopa.market.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.zopa.market.beans.ZopaMarket;

public class ParserMarketService {
	
	public List<ZopaMarket> parser(String pathMarket) throws FileNotFoundException, IOException {		
		
		List<ZopaMarket> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(pathMarket))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        records.add(new ZopaMarket(values[0], Double.valueOf(values[1]), Double.valueOf(values[2])));
		    }
		}
		
		return records;		
	}
	

}
