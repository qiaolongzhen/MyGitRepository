package cn.itheima.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import com.hankcs.lucene.HanLPAnalyzer;

public class LuceneTest {

	@Test
	public void createIndexTest1() throws IOException {
		// 1.指定索引库存放位置;
		FSDirectory directory = FSDirectory.open(Paths.get("D:\\temp\\index"));
		// 2.创建一个标准的分析器;
		StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
		// 3.创建一个IndexWriterConfig对象
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
		// 4.创建一个IndexWirter对象
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		// 5.设置原始文档路径
		File sourceDir = new File("D:\\temp\\source");
		for (File file : sourceDir.listFiles()) {
			// 创建Document对象
			Document document = new Document();
			// 设置域
			document.add(new StringField("fileName", file.getName(), Store.YES));
			document.add(new StoredField("filePath", file.getPath()));
			document.add(new TextField("fileContent", FileUtils.readFileToString(file), Store.YES));
			document.add(new LongPoint("fileSize", FileUtils.sizeOf(file)));
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}

	@Test
	public void createIndexTest2() throws IOException {
		// 1.指定索引库存放位置;
		FSDirectory directory = FSDirectory.open(Paths.get("D:\\temp\\index"));
		// 2.创建一个标准的分析器;
		Analyzer analyzer = new HanLPAnalyzer();
		// 3.创建一个IndexWriterConfig对象
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		// 4.创建一个IndexWirter对象
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		// 5.设置原始文档路径
		File sourceDir = new File("D:\\temp\\source");
		for (File file : sourceDir.listFiles()) {
			// 创建Document对象
			Document document = new Document();
			// 设置域
			document.add(new StringField("fileName", file.getName(), Store.YES));
			document.add(new StoredField("filePath", file.getPath()));
			document.add(new TextField("fileContent", FileUtils.readFileToString(file), Store.YES));
			document.add(new LongPoint("fileSize", FileUtils.sizeOf(file)));
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}

	@Test
	public void searchIndexTest() throws IOException {
		// 1.指定索引库存放位置;
		FSDirectory directory = FSDirectory.open(Paths.get("D:\\temp\\index"));
		// 2.创建IndexReader对象;
		IndexReader indexReader = DirectoryReader.open(directory);
		// 3.创建IndexSearcher对象;
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 4.创建查询对象;
		Query query = new TermQuery(new Term("fileName", "apache"));
		// 5.执行查询;
		// 参数一: 查询对象;
		// 参数二: 返回记录数;
		TopDocs topDocs = indexSearcher.search(query, 2);
		// 查询结果总条数;
		System.out.println("总纪录数: " + topDocs.totalHits);
		// 遍历结果
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			// 根据Document的id找到Document对象;
			Document document = indexSearcher.doc(scoreDoc.doc);
			System.out.println(document.get("fileName"));
			System.out.println(document.get("filePath"));
			System.out.println(document.get("fileSize"));
			System.out.println(document.get("fileContent"));
			System.out.println("-------------------------------------------------");
		}
		indexReader.close();
	}

}
