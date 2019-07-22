package com.zopa.market;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.zopa.market.beans.ResponseQuote;

public class ProcessTest extends UnitTestImportFileAbstract{

	
	@Test
    public void processTestSuccess() throws Exception{  	
		File file = getResourceFile("Market/market.csv");
		
		Process process = new Process();
		ResponseQuote response = process.init(file, 1500d);
		Assert.assertNotNull(response);
    }
	
	
	@Test
    public void processTestError() throws Exception{  		
		File file = getResourceFile("Market/market.csv");
		
		Process process = new Process();
		ResponseQuote response = process.init(file, 100d);
		Assert.assertNull(response);
		
		response = process.init(file, 1111d);
		Assert.assertNull(response);
		
		response = process.init(file, 17000d);
		Assert.assertNull(response);
    }
}
