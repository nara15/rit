package com.main;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.index.AbstractIndexer;
import com.index.AbstractSearcher;
import com.index.Indexer;
import com.index.Searcher;
import com.utils.LuceneConstants;
import com.utils.TextFileFilter;

public class LuceneTester {
	
	private String _indexPath = "C://RIT//index"; 
	private String _dataPath = "C://RIT";
	
	private AbstractIndexer _indexer;
	private AbstractSearcher _searcher;

	public static void main(String[] args)
	{
		LuceneTester tester;
		
		tester = new LuceneTester();
		
		try 
		{
			tester.createIndex();
			tester.search("56");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
	}
	
	private void createIndex() throws IOException
	{
		_indexer = new Indexer(this._indexPath);
		
		long startTime = System.currentTimeMillis();
		
		int numIndexed = this._indexer.createIndex(this._dataPath, new TextFileFilter());
		
		long endTime = System.currentTimeMillis();
		
		this._indexer.close();
		
		System.out.println(numIndexed + " files indexed, time taken: " 
							+ (endTime - startTime) + " ms");
	}
	
	
	private void search(String pSearchQuery) throws IOException, ParseException
	{
		this._searcher = new Searcher(this._indexPath);
		
		long startTime = System.currentTimeMillis();
		
		TopDocs hits = this._searcher.search(pSearchQuery);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(hits.totalHits + " documents found in " +
							(endTime - startTime) + " ms");
		
		for (ScoreDoc scoreDoc : hits.scoreDocs)
		{
			Document doc = this._searcher.getDocument(scoreDoc);
			
			System.out.println("File: " + doc.get(LuceneConstants.FILE_NAME));
		}
	}

}
