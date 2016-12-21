package InformationRetrivel.IR1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
public static void writefile(String filepath,String content){
	try {
		   File file = new File(filepath);

		   // if file doesnt exists, then create it
		   if (!file.exists()) {
		    file.createNewFile();
		   }

		   FileWriter fw = new FileWriter(file.getAbsoluteFile());
		   BufferedWriter bw = new BufferedWriter(fw);
		   bw.write(content);
		   bw.close();

		   System.out.println("Done");

		  } catch (IOException e) {
		   e.printStackTrace();
		  }
}
}
