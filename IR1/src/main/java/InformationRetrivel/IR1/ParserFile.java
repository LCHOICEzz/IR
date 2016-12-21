package InformationRetrivel.IR1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

public class ParserFile {
	private static Logger logger = Logger.getLogger(ParserFile.class);  
	public String stored_path = "D:\\Index";
	// 需要过滤<DOCHDR> <Address>标签内容
	public static void ParserHtmlFile(String filepath) throws IOException, SAXException, TikaException {
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(filepath));
		// FileInputStream inputstream = new FileInputStream(new
		// File("D:\\testhtml1.html"));
		ParseContext pcontext = new ParseContext();

		// Html parser
		HtmlParser htmlparser = new HtmlParser();

		htmlparser.parse(inputstream, handler, metadata, pcontext);
		System.out.println("Contents of the document:" + handler.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata.names();
		for (String name : metadataNames) {
			// System.out.println(name + ": " + metadata.get(name));
			System.out.println(metadata.get(name));
		}

	}

	public static void JsoupParser(String filepath) {
		ReadFile rf = new ReadFile();
		String html = rf.ReadFileToString(filepath);
		// System.out.print(html);
		Document doc = Jsoup.parse(html);
		Elements e1 = doc.getElementsByTag("DOCHDR");
		// e1.remove();
		System.out.println(e1.get(0).text());
		System.out.println(e1.get(1).text());
		// e1.get(0).removeAttr("");
		System.out.print(html);

	}

	public static void HtmlParser(String filepath) throws ParserException {
		
		ReadFile rf = new ReadFile();
		String html = rf.ReadFileToString(filepath);
        String[] section;
        int indexbegin_DOCHDR=0;
        int indexend_DOCHDR=0;
        int indexbegin_DOCNO=0;
        int indexend_DOCNO=0;
        int indexbegin_DOCOLDNO=0;
        int indexend_DOCOLDNO=0;
        //HtmlBean hb = new HtmlBean();
     // 建立hashmap专门用来存储(docid,htmlbean)
     	HashMap<String, Object> hashMap = new HashMap<String, Object>();
     	IndexFile ifile = new IndexFile();
     	
        section=html.replaceAll("</DOC>", "zouyu1").split("zouyu1");
        //section=html.split("<//DOC>");
        //System.out.print("section总数是:"+section.length);
       try{
        for(int i=0;i<section.length-1;i++){
//logger.info(i+":"+section[i]);
        indexbegin_DOCHDR = section[i].indexOf("<DOCHDR>");
   		indexend_DOCHDR = section[i].indexOf("</DOCHDR>");
   		if((indexbegin_DOCHDR==-1)||(indexend_DOCHDR==-1))
   			continue;
//   		System.out.println("DOCHDRContent-indexbegin:"+(indexbegin + 9)+"DOCHDRContent-indexend:"+indexend);
   		//logger.info(filepath+" "+i+" "+"DOCHDRContent-indexbegin:"+(indexbegin_DOCHDR)+"DOCHDRContent-indexend:"+indexend_DOCHDR);
   		String DOCHDRContent = section[i].substring(indexbegin_DOCHDR + 9, indexend_DOCHDR);
   		section[i] = section[i].replaceAll(DOCHDRContent, "");
//
   		indexbegin_DOCNO = section[i].indexOf("<DOCNO>");
   		indexend_DOCNO = section[i].indexOf("</DOCNO>");
   		if(indexbegin_DOCNO==-1||indexend_DOCNO==-1)
   			continue;
//   		System.out.println("DOCNO-indexbegin:"+(indexbegin + 7)+"DOCNO-indexend:"+indexend);
   		logger.info(filepath+" "+i+" "+"DOCNO-indexbegin:"+(indexbegin_DOCNO)+"DOCNO-indexend:"+indexend_DOCNO);
   		String DOCNO = section[i].substring(indexbegin_DOCNO + 7, indexend_DOCNO);
   		section[i] = section[i].replaceAll(DOCNO, "");

   		indexbegin_DOCOLDNO = section[i].indexOf("<DOCOLDNO>");
   		indexend_DOCOLDNO = section[i].indexOf("</DOCOLDNO>");
   		if(indexbegin_DOCOLDNO==-1||indexend_DOCOLDNO==-1)
   			continue;
//   		System.out.println("DOCOLDNO-indexbegin:"+(indexbegin + 10)+"DOCOLDNO-indexend:"+indexend);
   		//logger.info(filepath+" "+i+" "+"DOCOLDNO-indexbegin:"+(indexbegin_DOCOLDNO + 10)+"DOCOLDNO-indexend:"+indexend_DOCOLDNO);
   		String DOCOLDNO = section[i].substring(indexbegin_DOCOLDNO + 10, indexend_DOCOLDNO);
   		section[i] = section[i].replaceAll(DOCOLDNO, "");
        
		WriteFile.writefile(filepath, section[i]);
		Parser parser = Parser.createParser(section[i], "");
		HtmlPage page = new HtmlPage(parser);
		try {
			parser.visitAllNodesWith(page);
		} catch (ParserException e1) {
			e1.printStackTrace();
		}

		HtmlBean hb = new HtmlBean();
		hb.setTitle(page.getTitle());
		hb.setDOCHDR(DOCHDRContent);
		hb.setDOCNO(DOCNO);
		hb.setDOCOLDNO(DOCOLDNO);
		String Content=getContent(filepath);
		hb.setContent(Content);
		ifile.CreateIndex("D:\\Index",hb);
		hashMap.put(hb.getDOCNO(), hb);
		//logger.info("内容是:"+hb.getContent());
       }
        }catch(Exception e){
        	e.printStackTrace();
        }
          
//for(int i=0;i<2;i++){
//	System.out.println(i+":");
//	System.out.println(hb.getContent());
//}
//        for (String key : hashMap.keySet()) {  
//       	   logger.info("key:"+ key);  
//       	   logger.info("content:"+((HtmlBean)hashMap.get(key)).getContent());
//      	   logger.info("\n");
//       	  }

		//System.out.println(hb.getDOCNO());
       //index()
	}

	public static String getContent(String filepath) {
		StringBean sBean = new StringBean();
		sBean.setLinks(false);
		sBean.setCollapse(true);
		sBean.setReplaceNonBreakingSpaces(true);
		sBean.setURL(filepath);
		String content=sBean.getStrings();
		return content;
	}
}
