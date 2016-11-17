package com.utils;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class GetIndexDirectory 
{
	public static Directory getIndexDirectory(String pIdexPath) throws IOException
	{
		return FSDirectory.open(Paths.get(pIdexPath));
	}
}
