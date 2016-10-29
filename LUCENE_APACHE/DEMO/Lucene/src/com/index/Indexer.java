package com.index;

import java.io.IOException;

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

	

}
