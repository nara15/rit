package com.search;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.utils.LuceneConstants;

public class Searcher extends AbstractSearcher
{

	public Searcher(String pIndexDirectoryPath) throws IOException
	{
		super(pIndexDirectoryPath);
		
	}

	@Override
	public TopDocs search(String pQuery) throws IOException, ParseException
	{
		this._query = this._queryParser.parse(pQuery);
		
		return this._indexSearcher.search(_query, LuceneConstants.MAX_SEARCH);
	}

	@Override
	public Document getDocument(ScoreDoc pScoreDoc) throws CorruptIndexException, IOException
	{
		return this._indexSearcher.doc(pScoreDoc.doc);
	}

	@Override
	public void close() throws IOException
	{
		this._indexSearcher.getIndexReader().close();		
	}

}
