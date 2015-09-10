//: c12:JGrep.java
// A very simple version of the "grep" program.
// {Args: JGrep.java "\\b[Ssct]\\w+"}
// From 'Thinking in Java, 3rd ed.' (c) Bruce Eckel 2002
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
import java.io.*;
import java.util.regex.*;
import java.util.*;
import com.bruceeckel.util.*;

public class JGrep {
  public static void main(String[] args) throws Exception {
    if(args.length < 2) {
      System.out.println("Usage: java JGrep file regex");
      System.exit(0);
    }
    String regex = args[1];
    String fileName = args[0];
    File file = new File(fileName);
    if(file.isFile()) {
      search(fileName, regex);
    }
    if(file.isDirectory()) {
      String[] fileList = file.list();
      for(int i = 0; i < fileList.length; i++) {
        search(fileList[i], regex);
      }
    }
    
  }
  public static void search(String fileName, String regex)
  throws Exception {
    Pattern p = Pattern.compile(regex);
    // Iterate through the lines of the input file:
    ListIterator it = new TextFile(fileName).listIterator();
    while(it.hasNext()) {
      Matcher m = p.matcher((String)it.next());
      while(m.find())
        System.out.println(it.nextIndex() + ": " +
          m.group() + ": " + m.start());
    }
  }
} ///:~
