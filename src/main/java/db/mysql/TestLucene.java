package db.mysql;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * Hello world!
 * 
 */
public class TestLucene {
	public static void main(String[] args) throws Exception {
		Directory index = new RAMDirectory();

		IndexWriter w = new IndexWriter(index, new IndexWriterConfig(Version.LUCENE_34, new StandardAnalyzer(Version.LUCENE_34)).setOpenMode(OpenMode.CREATE));
		addDoc(w, "abc", "11");
		addDoc(w, "bcd", "12");
		addDoc(w, "cde", "13");
		addDoc(w, "abf", "14");
		w.close();

		Sort nameSort = new Sort();
		nameSort.setSort(new SortField("name", SortField.STRING, false));

		Sort gfcIdSort = new Sort();
		gfcIdSort.setSort(new SortField("gfcid", SortField.STRING, false));

		IndexReader reader = IndexReader.open(index);
		IndexSearcher searcher = new IndexSearcher(reader);

		Query query = new PrefixQuery(new Term("sname", "b"));
		// Query query = new PrefixQuery(new Term("name", "ab"));
		TopScoreDocCollector collector = TopScoreDocCollector.create(10, false);

		searcher.search(query, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		// 4. display results
		System.out.println("Found " + hits.length + " hits.");
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println(d.get("id") + "\t" + d.get("name"));
		}

		// reader can only be closed when there
		// is no need to access the documents any more.
		reader.close();
	}

	private static void addDoc(IndexWriter w, String value, String id) throws IOException {
		try {
			Document document = new Document();
			document.add(new Field("name", value, Field.Store.YES, Field.Index.NOT_ANALYZED));
			document.add(new Field("id", id, Field.Store.YES, Field.Index.NOT_ANALYZED));
			document.add(new Field("sname", value.substring(1), Field.Store.YES, Field.Index.NOT_ANALYZED));
			w.addDocument(document);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}

