package com.zopa.market;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Program {
	
	private static final Logger logger = LogManager.getLogger(Program.class);

	public static void main(String[] args) {

		if(args.length>2 || args.length<2) {
			logger.error("2 args are required. 1 Market path 2 loan amount");
		}
		
		try {
			Process process = new Process();
			process.init(new File(args[0]), Double.parseDouble(args[1]));

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
