package utils;

import java.io.File;
import java.io.FileFilter;

public class TextFileFilter implements FileFilter
{

	@Override
	public boolean accept(File pPathName)
	{
		return pPathName.getName().toLowerCase().endsWith(".txt");
	}
	
}
