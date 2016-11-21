package utils;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Utility class for getting drectory to inverted index path.
 * @author Jose Mario
 */
public class GetIndexDirectory 
{

    /**
     *
     * @param pIdexPath Path to inverted index.
     * @return A Lucene directory class.
     * @throws IOException If the path does not exist.
     */
    public static Directory getIndexDirectory(String pIdexPath) throws IOException
    {
        return FSDirectory.open(Paths.get(pIdexPath));
    }
}
