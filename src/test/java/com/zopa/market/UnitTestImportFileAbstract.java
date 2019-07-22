package com.zopa.market;

import java.io.File;

public abstract class UnitTestImportFileAbstract {
	
	
	public File getResourceFile(String name) {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource(name).getFile());
	}

}
