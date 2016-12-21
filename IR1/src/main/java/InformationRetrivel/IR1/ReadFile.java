package InformationRetrivel.IR1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
	/*
	public  static String ReadFileToString(String filepath){
		//String str="";
		try {
	       // BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath))));
	        StringBuilder stringBuilder = new StringBuilder();
	        String content;
	        while((content = bufferedReader.readLine() )!=null){
	            stringBuilder.append(content);
	            stringBuilder.append("\n");
	        }
	        return stringBuilder.toString();
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        return null;
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        return null;
	    }
		
		//return str;
	}
*/
	public static String ReadFileToString(String filepath) {
		// TODO Auto-generated method stub
		try {
		       // BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath))));
		        StringBuilder stringBuilder = new StringBuilder();
		        String content;
		        while((content = bufferedReader.readLine() )!=null){
		            stringBuilder.append(content);
		            stringBuilder.append("\n");
		        }
		        return stringBuilder.toString();
		    } catch (FileNotFoundException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		        return null;
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		        return null;
		    }
	}
	  public static String[] readfilelist(String filepath) throws FileNotFoundException, IOException {
          String[] filelist=null;
		  //try {
                  File file = new File(filepath);
                  if (!file.isDirectory()) {
                         System.out.println("文件");
                          System.out.println("path=" + file.getPath());
                          System.out.println("absolutepath=" + file.getAbsolutePath());
                          System.out.println("name=" + file.getName());
                	  
                  } else if (file.isDirectory()) {
                          //System.out.println("文件夹");
                           filelist = file.list();
//                          for (int i = 0; i < filelist.length; i++) {
//                                  File readfile = new File(filepath + "\\" + filelist[i]);
//                                  if (!readfile.isDirectory()) {
//                                          System.out.println("path=" + readfile.getPath());
//                                          System.out.println("absolutepath="
//                                                          + readfile.getAbsolutePath());
//                                          System.out.println("name=" + readfile.getName());
//
//                                  } else if (readfile.isDirectory()) {
//                                          readfile(filepath + "\\" + filelist[i]);
//                                  }
//                          }

                  }
                  
//          } catch (FileNotFoundException e) {
//                  System.out.println("readfile()   Exception:" + e.getMessage());
//          }
          //return ReadFileToString(filepath);
          return filelist;
          
  }
}
