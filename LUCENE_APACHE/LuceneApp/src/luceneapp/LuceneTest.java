package luceneapp;

import java.io.IOException;
import model.indexer.TXT_Indexer;

import utils.TextFileFilter;



public class LuceneTest {
	
	private final String _indexPath = "C://RIT//index"; 
	private final String _dataPath = "C://RIT//";
	
	private TXT_Indexer _indexer;
	

	public static void main(String[] args)
	{
		LuceneTest tester;
		
		tester = new LuceneTest();
		
		try 
		{
			tester.createIndex();
		} 
		catch (IOException e)
		{
		}
	}
	
	private void createIndex() throws IOException
	{
		_indexer = new TXT_Indexer(this._indexPath);
		
		long startTime = System.currentTimeMillis();
		
		int numIndexed = this._indexer.createIndex(this._dataPath, new TextFileFilter());
		
		long endTime = System.currentTimeMillis();
		
		this._indexer.close();
		
		System.out.println(numIndexed + " files indexed, time taken: " 
							+ (endTime - startTime) + " ms");
	}
	
	
	

}
