package trx.sales.xbr;

import java.io.File;

public class FileLength {
	public static Long getFileSizeInKb(String filename) {
	      File file = new File(filename);
	      if (!file.exists() || !file.isFile()) {
	         //System.out.println("File doesn\'t exist");
	         return Long.valueOf(-1);
	      }
	      return Long.valueOf((int)Math.ceil(file.length()/1024));
	   }
}
