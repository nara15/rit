package luceneapp;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import utils.LuceneConstants;



public class Test_Search 
{
	private static String _indexPath = "C://RIT//index"; 
	
	public static void main(String[] args)
	{
		
		Directory indexDir;
				
		try 
		{
			indexDir = utils.GetIndexDirectory.getIndexDirectory(_indexPath);
			
			IndexReader r = DirectoryReader.open(indexDir);
			
			IndexSearcher searcher = new IndexSearcher(r);
			
                        
                        // BOOLEAN QUERY --> COLOMBIA AND COFFEE
//                        BooleanQuery.Builder bq = new BooleanQuery.Builder();
//                        bq.add(new TermQuery(new Term(utils.ReutersConstants.TITLE,"colombia")), BooleanClause.Occur.MUST);
//                        bq.add(new TermQuery(new Term(utils.ReutersConstants.TITLE, "coffee")), BooleanClause.Occur.MUST);
//                        BooleanQuery q = bq.build();
                        
                        // PHRASE QUERY
                        PhraseQuery.Builder builder = new PhraseQuery.Builder();
                        builder.add(new Term(utils.ReutersConstants.CONTENT, "heavy"), 0);
                        builder.add(new Term(utils.ReutersConstants.CONTENT, "rains"), 1);
                        PhraseQuery q = builder.build();
                        
                        
                        // COFFEE AND HEAVY RAINS
//                        PhraseQuery.Builder builder = new PhraseQuery.Builder();
//                        builder.add(new Term(utils.ReutersConstants.CONTENT, "heavy"), 0);
//                        builder.add(new Term(utils.ReutersConstants.CONTENT, "rains"), 1);
//                        PhraseQuery q1 = builder.build();
//                        BooleanQuery.Builder bq = new BooleanQuery.Builder();
//                        bq.add(q1, BooleanClause.Occur.MUST);
//                        bq.add(new TermQuery(new Term(utils.ReutersConstants.TITLE, "coffee")), BooleanClause.Occur.MUST);
//                        BooleanQuery q = bq.build();

                        
//			Term t = new Term(utils.ReutersConstants.CONTENT, "colombia");
//			Query q = new TermQuery(t);
			
			TopDocs docs = searcher.search(q, 10);
			
			System.out.println(docs.totalHits);
			
			for (ScoreDoc scoreDoc : docs.scoreDocs)
			{
				Document doc = searcher.doc(scoreDoc.doc);
				
				System.out.println("File: " + doc.get(LuceneConstants.FILE_NAME));
			}
			
		} 
		catch (IOException e)
		{
			
			e.printStackTrace();
			
		}

	}

}
