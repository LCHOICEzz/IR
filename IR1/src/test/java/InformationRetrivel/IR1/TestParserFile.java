package InformationRetrivel.IR1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.tika.exception.TikaException;
import org.htmlparser.util.ParserException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class TestParserFile {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IOException, SAXException, TikaException, ParserException, URISyntaxException {


		//String basefilepath="D:\\WT10G";
		String basefilepath="D:\\Test";
		//String filepath="D:\\testhtml.txt";
		ParserFile pf=new ParserFile();
		//pf.HtmlParser(filepath);
		//ReadFile rf=new ReadFile();
		String[] folder=null;
		//String[]filepath=new String[5157];
		String[]filepath=new String[100];
		int k=0;
		String[] filelist=ReadFile.readfilelist(basefilepath);
		String[] innerfilelist=new String[filelist.length];
		
		for(int i=0;i<filelist.length;i++){
			
			innerfilelist[i]=basefilepath+"\\"+filelist[i];
			folder=ReadFile.readfilelist(innerfilelist[i]);
			for(int j=0;j<folder.length;j++){
				//System.out.println(innerfilelist[i]+"\\"+folder[j]);
				//filepath[i][j]=innerfilelist[i]+"\\"+folder[j];
				filepath[k++]=innerfilelist[i]+"\\"+folder[j];
			}
		}
		for(int i=0;i<filepath.length;i++)
				pf.HtmlParser(filepath[i]);

//		for(int i=0;i<innerfilelist.length;i++)
//			{
//			System.out.println(filelist[i]);
//			System.out.println(innerfilelist[i]);
//			}
		
//		for(int i=0;i<folder.length;i++){
//			System.out.println(folder[i]);
//		}
			
//		URI base=new URI("http://sdtech.com:80/sandiego/index.html");
//		URI abs=base.resolve("sdlinks.html");//解析于上述网页的相对URL，得到绝对URI
//		URL absURL=abs.toURL();//转成URL
//		System.out.println(absURL);
	}

}
