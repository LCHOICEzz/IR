package InformationRetrivel.IR1;

import java.io.File;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.lucene.util.Version;

public class IndexFile {
public IndexWriter writer=null;	
	//public IndexWriter writer = null;
	public void CreateIndex(String stored_path, HtmlBean hb) throws IOException {
		
		if (stored_path == null) {
			stored_path = "D:\\Index";
		}
		final File indexDir = new File(stored_path);
		Directory IndexDir = FSDirectory.open(indexDir);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_CURRENT, new StandardAnalyzer(Version.LUCENE_48));
		writer = new IndexWriter(IndexDir, iwc);
		
		if (!indexDir.exists() || !indexDir.canRead()) {
			System.out.println("Document directory '" + indexDir.getAbsolutePath()
					+ "' does not exist or is not readable, please check the path");
			System.exit(1);
		}
		try {
			// 1、创建索引库IndexWriter
//			if (writer == null) {
//				initialIndexWriter(indexDir);
//			}
			index(writer, hb);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.commit();
			writer.close();
		}

	}

	public void index(IndexWriter writer, HtmlBean hb) throws IOException {
		Document doc = new Document();
		// Field pathField = new StringField("path", filetoIndex.getPath(),
		// Field.Store.YES);
		// doc.add(pathField);
		// doc.add(new LongField("modified", filetoIndex.lastModified(),
		// Field.Store.YES));
		// doc.add(new StringField("title", filetoIndex.getName(),
		// Field.Store.YES));
		// doc.add(new TextField("contents", new FileReader(filetoIndex)));
		// System.out.println("Indexing " + filetoIndex.getName());
		doc.add(new StringField("DOCHDR", hb.getDOCHDR(), Field.Store.YES));
		doc.add(new StringField("DONO", hb.getDOCNO(), Field.Store.YES));
		doc.add(new StringField("DOCOLDNO", hb.getDOCOLDNO(), Field.Store.YES));
		doc.add(new StringField("Content", hb.getContent(), Field.Store.YES));
		doc.add(new TextField("Title", hb.getTitle(), Field.Store.YES));
		// 3、向索引库中写入文档内容
		writer.addDocument(doc);
	}

	/*
	 * public void indexAllFileinDirectory(String indexPath) throws IOException
	 * { if (indexPath == null) { indexPath = "D:\\Index"; } final File indexDir
	 * = new File(indexPath); if (!indexDir.exists() || !indexDir.canRead()) {
	 * System.out.println("Document directory '" + indexDir.getAbsolutePath() +
	 * "' does not exist or is not readable, please check the path");
	 * System.exit(1); } try { // 1、创建索引库IndexWriter if (writer == null) {
	 * initialIndexWriter(indexDir); } index(writer, indexDir); } catch
	 * (IOException e) { e.printStackTrace(); } finally { writer.close(); } }
	 */
	// 使用了最简单的单例模式，用于返回一个唯一的IndexWirter，注意此处非线程安全，需要进一步优化。
	private void initialIndexWriter(File indexDir) throws IOException {
		Directory returnIndexDir = FSDirectory.open(indexDir);
		@SuppressWarnings("deprecation")
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_CURRENT, new StandardAnalyzer(Version.LUCENE_48));
		writer = new IndexWriter(returnIndexDir, iwc);
	}
	/*
	 * private void index(IndexWriter writer, File filetoIndex) throws
	 * IOException { if (filetoIndex.isDirectory()) { String[] files =
	 * filetoIndex.list(); if (files != null) { for (int i = 0; i <
	 * files.length; i++) { index(writer, new File(filetoIndex, files[i])); } }
	 * } else { // 2、根据文件创建文档Document，考虑一下能否不用每次创建Document对象 Document doc = new
	 * Document(); Field pathField = new StringField("path",
	 * filetoIndex.getPath(), Field.Store.YES); doc.add(pathField); doc.add(new
	 * LongField("modified", filetoIndex.lastModified(), Field.Store.YES));
	 * doc.add(new StringField("title", filetoIndex.getName(),
	 * Field.Store.YES)); doc.add(new TextField("contents", new
	 * FileReader(filetoIndex))); // System.out.println("Indexing " +
	 * filetoIndex.getName());
	 * 
	 * // 3、向索引库中写入文档内容 writer.addDocument(doc); } }
	 */
}
