
package model.search;

import java.io.IOException;
import model.stemming.IStemmingStrategy;
import model.stemming.NullStemmingStrategy;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public abstract class AbstractSearcher 
{

    /**
     * The indexSearcher from Lucene
     */
    protected IndexSearcher _indexSearcher;
    
    /**
     * The stemming algorithm strategy for the query parsing
     */
    protected IStemmingStrategy stemmer;
    
    /**
     * Creates a new Indexer 
     * @param pIndexDirectoryPath Path to the inverted index
     * @throws IOException if the path does not exist
     */
    public AbstractSearcher(String pIndexDirectoryPath) throws IOException
    {
        Directory indexDir = utils.GetIndexDirectory.getIndexDirectory(pIndexDirectoryPath);
        
        IndexReader r = DirectoryReader.open(indexDir);
        
        this._indexSearcher = new IndexSearcher(r);    
        
        this.stemmer = new NullStemmingStrategy();
    }
    
    /**
     * Process the term to get the stemmed one.
     * @param pTerm The term to be stemmed
     * @return the stemmed term according to the stemming algorithm
     */
    public abstract String stemmedWord(String pTerm);

    /**
     *
     * @param stemmer The algorithm strategy
     */
    public void setStemmer(IStemmingStrategy stemmer)
    {
        this.stemmer = stemmer;
    }

    /**
     *
     * @return The index seacher
     */
    public IndexSearcher getIndexSearcher()
    {
        return _indexSearcher;
    }
    
    
    
}
