package trx.sales.etps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.ibm.broker.plugin.MbTimestamp;



public class DateHelper {
	
	public static String getTimestampInMST(MbTimestamp inputDate) {
		Date date = inputDate.getTime();
		String outputDate;
		//DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Tell the DateFormat that I want to show the date in the MST timezone
		df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
		outputDate = df.format(date);
		return outputDate;		
	}
	
	/*
	 * returns the current timezone offset in Calgary (-7 or -6)
	 */
	public static String getCurrentTimeZoneOffset(){
		
		boolean dst = TimeZone.getTimeZone("US/Mountain").inDaylightTime(new Date());
		
		if (dst) 
			return "-06:00";
		else 
			return "-07:00";
		
		
		
	}

}
