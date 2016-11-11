package com.main;

import java.io.IOException;

import com.index.AbstractIndexer;
import com.index.Indexer;
import com.utils.TextFileFilter;

public class LuceneTester {
	
	private String _indexPath = "C://RIT//index"; 
	private String _dataPath = "C://RIT";
	
	private AbstractIndexer _indexer;

	public static void main(String[] args)
	{
		LuceneTester tester;
		
		tester = new LuceneTester();
		
		try 
		{
			tester.createIndex();
		} 
		catch (IOException e)
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

}
