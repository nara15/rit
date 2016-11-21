package model.indexer;

import java.io.IOException;
import java.nio.file.Paths;
import model.stemming.IStemmingStrategy;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @author Jose Mario Naranjo Leiva
 *
 */

public abstract class AbstractIndexer 
{	

    /**
     * The Lucene Lib index writer
     */
    protected IndexWriter _writer;

    /**
     * Stemming algorithm strategy
     */
    protected IStemmingStrategy stemmer;

    /**
     * Creates an indexer in the specified path
     * @param pIndexDirectoryPath Path for the inverted ndex
     * @throws IOException if the index path cannot be accessed or created
     */
    public AbstractIndexer(String pIndexDirectoryPath) throws IOException
    {
        StandardAnalyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        Directory indexDirectory =  FSDirectory.open(Paths.get(pIndexDirectoryPath));

        _writer = new IndexWriter(indexDirectory, config);
    }
    
    /**
     * Closes the Lucene writter.
     * @throws IOException If the Lucene writter is not initilized.
     */
    public abstract void close() throws IOException;
    
    /**
     * Sets a stemmng algorithm strategy.
     * @param pStemmer the stemmer instance.
     */
    public void setStemmer(IStemmingStrategy pStemmer)
    {
        this.stemmer = pStemmer;
    }

}
