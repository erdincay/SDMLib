package org.sdmlib.storyboards;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class CallDot {
	private static void writeToFile(String imgName, String fileText) throws IOException
	{
	   File docDir = new File("doc");
      
      docDir.mkdir();
      
      int lastSlash = imgName.lastIndexOf('/');
      if (lastSlash >= 0)
      {
         String subDir = imgName.substring(0, lastSlash);
         docDir = new File("doc/"+subDir);
         
         docDir.mkdirs();
      }
	   
		BufferedWriter out;
		out = new BufferedWriter(new FileWriter("doc/" + imgName + ".dot"));

		out.write(fileText);
		out.close();
	}

	public static void callDot(String imgName, String dotContent) {
//		BufferedWriter out; 
	      String[] command = null;
	      
	      File docDir = new File("doc");
	      
	      docDir.mkdir();
	      
	      int lastSlash = imgName.lastIndexOf('/');
	      if (lastSlash >= 0)
	      {
	         String subDir = imgName.substring(0, lastSlash);
	         docDir = new File("doc/"+subDir);
	         
	         docDir.mkdirs();
	      }

	      URL absolutePath = CallDot.class.getResource("../../SDMLib.gwt.xml");
	      if(absolutePath==null){
	    	  return;
	      }
	      if ((System.getProperty("os.name").toLowerCase()).contains("windows")) 
	      {
	    	  File root = new File(""+absolutePath).getParentFile().getParentFile().getParentFile();
	    	  String rootPath = root.getPath().replace("file:\\", "");
	    	  String makeimageFile = rootPath+"/tools/makeimage.bat";
	    	  
//	    	  makeimage.bat
//	     	  String makeimageFile = "../SDMLib.net/tools/makeimage.bat";
//	     	  if ( ! new File(makeimageFile).exists())
//	     	  {
//	     	     makeimageFile = "../SDMLib/SDMLib.net/tools/makeimage2.bat";
//	     	  }
	     	  command = new String [] {makeimageFile, imgName, rootPath};
	      }
	      else if ((System.getProperty("os.name").toLowerCase()).contains("mac")) {
	    	  
	    	  File root = new File(""+absolutePath).getParentFile().getParentFile().getParentFile();
	    	  String rootPath = root.getPath().replace("file:", "");
	    	  String makeimageFile = rootPath+"/tools/Graphviz/osx_lion/makeimage.command";
	    	  
	    	  command = new String [] {makeimageFile, imgName, rootPath};
	      }
	      else { // let's assume it's linux'ish (works also for mac)
	    	  command = new String [] {"dot","doc/"+imgName+".dot","-Tsvg","-o","doc/"+imgName+".svg"};
	      }
	      try {
	    	  writeToFile(imgName, dotContent);
	    	  ProcessBuilder processBuilder =  new ProcessBuilder(command);
	    	  processBuilder.redirectErrorStream(true);
	    	  Process child = processBuilder.start();
	    	  InputStream inputStream = child.getInputStream();
	    	  BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
	    	  String line = null; 
	    	  while ((line = buf.readLine()) != null)
	    	  {
	    	     System.out.println(line);
	    	  }
	    	  
	    	  // int returnValue = child.waitFor();
	    	  // if (returnValue != 0 ) {
			  //    System.err.println("execute dot failed");
	    	  // }
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      
			
//			   BufferedWriter out;
	//
//			   File dotFile = new File("doc/" + diagName + ".dot");
//			   StoryboardManager.get().printFile(dotFile, dotFileText.toString());
	}

}
