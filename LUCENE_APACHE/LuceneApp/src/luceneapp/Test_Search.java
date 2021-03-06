package luceneapp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.search.AbstractSearcher;
import model.search.Reuters_Searcher;
import model.stemming.SimpleStemmingStrategy;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.surround.parser.ParseException;
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
import utils.ReutersConstants;



public class Test_Search 
{
	private static final String _indexPath = "C://RIT//index"; 
	
	public static void main(String[] args)
	{
		
//		Directory indexDir;
//				
		try 
		{
//			indexDir = utils.GetIndexDirectory.getIndexDirectory(_indexPath);
//			
//			IndexReader r = DirectoryReader.open(indexDir);
//			
//			IndexSearcher searcher = new IndexSearcher(r);
//                        
//                        AbstractSearcher search = new Reuters_Searcher(_indexPath);
//                        search.setStemmer(new SimpleStemmingStrategy());
                        
                        // BOOLEAN QUERY --> COLOMBIA AND COFFEE
//                        String term1 = search.stemmedWord("colombia");
//                        String term2 = search.stemmedWord("coffee");
//                        BooleanQuery.Builder bq = new BooleanQuery.Builder();
//                        bq.add(new TermQuery(new Term(utils.ReutersConstants.TITLE,term1)), BooleanClause.Occur.MUST);
//                        bq.add(new TermQuery(new Term(utils.ReutersConstants.TITLE, term2)), BooleanClause.Occur.MUST);
//                        BooleanQuery q = bq.build();
                        
                        // PHRASE QUERY
//                        String term1 = search.stemmedWord("heavy");
//                        String term2 = search.stemmedWord("rains");
//                        PhraseQuery.Builder builder = new PhraseQuery.Builder();
//                        builder.add(new Term(utils.ReutersConstants.CONTENT, term1), 0);
//                        builder.add(new Term(utils.ReutersConstants.CONTENT, term2), 1);
//                        PhraseQuery q = builder.build();
//                        
                        
                        // COFFEE AND HEAVY RAINS
//                        String term1 = search.stemmedWord("heavy");
//                        String term2 = search.stemmedWord("rains");
//                        String term3 = search.stemmedWord("coffee");
//                        PhraseQuery.Builder builder = new PhraseQuery.Builder();
//                        builder.add(new Term(utils.ReutersConstants.CONTENT, term1), 0);
//                        builder.add(new Term(utils.ReutersConstants.CONTENT, term2), 1);
//                        PhraseQuery q1 = builder.build();
//                        BooleanQuery.Builder bq = new BooleanQuery.Builder();
//                        bq.add(q1, BooleanClause.Occur.MUST);
//                        bq.add(new TermQuery(new Term(utils.ReutersConstants.TITLE, term3)), BooleanClause.Occur.MUST);
//                        BooleanQuery q = bq.build();
                        
                        AbstractSearcher search = new Reuters_Searcher(_indexPath);
                        search.setStemmer(new SimpleStemmingStrategy());
                        
                        QueryParser _queryParser;
                        _queryParser = new QueryParser(ReutersConstants.CONTENT, new EnglishAnalyzer());
                        String term11 = search.stemmedWord("colombia");
                        String term22 = search.stemmedWord("coffee");
                        //String special = "title:" + term22 + " AND title:" + term11;
                        String special = "contents: \"heavy rains\"";
                        Query q1 = _queryParser.parse(special);
                        
                        System.out.println(search.search(special).size());
                        
//                        Analyzer a = new EnglishAnalyzer();

//			TopDocs docs = searcher.search(q1, 30);
//			
//			System.out.println(docs.totalHits + " NOTICIAS RECUPERADAS");
//			
//			for (ScoreDoc scoreDoc : docs.scoreDocs)
//			{
//				Document doc = searcher.doc(scoreDoc.doc);
//				
//				System.out.println("File: " + doc.get(LuceneConstants.FILE_NAME) + " <" +
//                                        doc.get(utils.ReutersConstants.HEADER));
//			}
			
		} 
		catch (IOException | org.apache.lucene.queryparser.classic.ParseException ex)
		{
		
                Logger.getLogger(Test_Search.class.getName()).log(Level.SEVERE, null, ex);
            }

	}

}
