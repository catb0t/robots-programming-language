import java.util.*;
import java.io.*;

// From: http://www.ibm.com/developerworks/java/library/j-tiger02254.html

public class LoadProperties {
   public static void main(String args[]) throws Exception {
      Properties prop = new Properties();
      FileInputStream fis = 
         new FileInputStream("sample.properties");
      prop.load(fis);
      prop.list(System.out);
      System.out.println("\nThe foo property: " +
            prop.getProperty("foo"));
   }
}

