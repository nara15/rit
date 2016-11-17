package com.main;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import com.utils.LuceneConstants;

public class Test_Search 
{
	private static String _indexPath = "C://RIT//index"; 
	
	public static void main(String[] args)
	{
		
		Directory indexDir;
				
		try 
		{
			indexDir = com.utils.GetIndexDirectory.getIndexDirectory(_indexPath);
			
			IndexReader r = DirectoryReader.open(indexDir);
			
			IndexSearcher searcher = new IndexSearcher(r);
			
			Term t = new Term(com.utils.LuceneConstants.CONTENTS, "cocoa");
			
			Query q = new TermQuery(t);
			
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
