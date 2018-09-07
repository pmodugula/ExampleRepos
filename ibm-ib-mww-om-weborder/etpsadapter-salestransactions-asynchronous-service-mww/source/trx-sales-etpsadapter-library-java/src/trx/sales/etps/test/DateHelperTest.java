package trx.sales.etps.test;

import org.junit.Test;

import trx.sales.etps.DateHelper;

public class DateHelperTest {

	
	@Test
	public void testgetCalgaryTimeZoneOffset(){
		
		
		String result = DateHelper.getCurrentTimeZoneOffset();
		
		System.out.println(result);
		
		
		
		
	}
	
	
	
}
