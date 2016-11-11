package com.index;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import com.utils.LuceneConstants;

/**
 * @author José Mario Naranjo Leiva
 *
 */
public class Indexer extends AbstractIndexer
{
	
	
	public Indexer(String pIndexDirectoryPath) throws IOException
	{
		super(pIndexDirectoryPath);
		
	}

	@Override
	public void close() throws IOException
	{
		this._writer.close();
	}
	
	@Override
	public int createIndex(String pDataDirPath, FileFilter pFilter) throws IOException
	{
		// All files in the data directory
		File[] allFiles = new File(pDataDirPath).listFiles();
		
		for (File file : allFiles)
		{
			if (!file.isDirectory()
					&& !file.isHidden()
					&& file.exists()
					&& file.canRead()
					&& pFilter.accept(file))
			{
				this.indexFile(file);
			}
		}
		
		return this._writer.numDocs();
	}
	
	
	private Document getDocument(File pFile) throws IOException
	{
		Document document = new Document();
		
		// Index file contents
		TextField contentField = new TextField(LuceneConstants.CONTENTS, new FileReader(pFile));
		
		// Index file name
		TextField fileNameField = new TextField(LuceneConstants.FILE_NAME, pFile.getName(),Field.Store.YES);
		
		// Index file path
		TextField filePathField = new TextField(LuceneConstants.FILE_PATH, pFile.getCanonicalPath(), Field.Store.YES);
		
		document.add(contentField);
		document.add(fileNameField);
		document.add(filePathField);
		
		return document;
	}
	
	private void indexFile(File pFile) throws IOException
	{
		System.out.println("Indexing " + pFile.getCanonicalPath());
		
		Document document = this.getDocument(pFile);
		
		this._writer.addDocument(document);
	}

	
	
	
	
	

	

}
