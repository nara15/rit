package com.index;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.utils.LuceneConstants;

public abstract class AbstractSearcher
{
	protected IndexSearcher _indexSearcher;
	protected QueryParser _queryParser;
	protected Query _query;
	
	public AbstractSearcher(String pIndexDirectoryPath) throws IOException
	{
		// Initializes the index searcher
		Directory indexDirectory =  FSDirectory.open(Paths.get(pIndexDirectoryPath));
		
		IndexReader indexReader =  DirectoryReader.open(indexDirectory);
		
		this._indexSearcher = new IndexSearcher(indexReader);
		
		// Initializes the query parser
		this._queryParser = new QueryParser(LuceneConstants.CONTENTS, new StandardAnalyzer());
	}
	
	public abstract TopDocs search(String pQuery) throws IOException, ParseException;
	
	public abstract Document getDocument(ScoreDoc pScoreDoc) throws CorruptIndexException, IOException;
	
	public abstract void close() throws IOException;

}
