package InformationRetrivel.IR1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testfile1 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		//public static void  TestReadFile(){
			ReadFile rfts=new ReadFile();
			String filepath="D:\\testhtml.txt";
			String htmlcontent=rfts.ReadFileToString(filepath);
			System.out.println(htmlcontent);
			//ReadFile rf=new ReadFile();
			//String htmlcontent=ReadFile.ReadFileToString(filepath);
			//System.out.println(htmlcontent);
			//System.out.print("hello");
		
	}

}
