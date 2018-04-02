package com.backendIntern.batchConfig;

import org.springframework.batch.item.ItemProcessor;

import com.backendIntern.model.logLine;


public class FiltringItemProcessor implements ItemProcessor<String, logLine> {

	@Override
	public logLine process(String line) throws Exception {
		
		if(!line.contains("[{")) {

		return null;
		
		}else
		{
			
			System.out.println("*******"+line.substring(0,8));
			//System.out.println("*******"+line.substring(38));

	        final logLine logligne = new logLine("", line.substring(0,8), "SLE file" , line.substring(38));

			
			
			return logligne;
		}
	}
	
	
	

}
