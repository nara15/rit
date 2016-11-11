package com.index;

import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/**
 * @author José Mario Naranjo Leiva
 *
 */
public abstract class AbstractIndexer 
{
	
	protected IndexWriter _writer;
	
	public AbstractIndexer(String pIndexDirectoryPath) throws IOException
	{
		StandardAnalyzer analyzer = new StandardAnalyzer();
		
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		
		Directory indexDirectory =  FSDirectory.open(Paths.get(pIndexDirectoryPath));
		
		_writer = new IndexWriter(indexDirectory, config);
		
	}
	
	public abstract void close() throws IOException;
	
	public abstract int createIndex(String pDataDirPath, FileFilter pFilter) throws IOException;
}
