package com.fgl.eai;



import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbGlobalMap;

public class CacheUtil {
  /**
   * Method to get a value from Global Cache using map name and key
   */
  public static String getValue(String strMapName, String strKey) {
    
    String strValue = null;
    MbGlobalMap globalMap = null;
    
    try
    {
      globalMap = MbGlobalMap.getGlobalMap(strMapName);
      strValue = (String) globalMap.get(strKey);
    }
    catch(MbException mbe)
    {
      System.out.println(mbe.getMessage());
      mbe.printStackTrace();
    }
    
    return strValue;
  }
  
  /**
   * Method to add a key-value pair to a map in Global Cache
   */
  public static Boolean addUpdateKey(String strMapName, String strKey, String strValue) {
    
    MbGlobalMap globalMap = null;
    
    try
    {
      globalMap = MbGlobalMap.getGlobalMap(strMapName);
      
      if(globalMap.containsKey(strKey)) {
        globalMap.update(strKey,strValue);
      } else {
        globalMap.put(strKey, strValue);
      }
    }
    catch(MbException mbe) {
      System.out.println(mbe.getMessage());
      mbe.printStackTrace();
      return Boolean.FALSE;
    }

    return Boolean.TRUE;
  }

}