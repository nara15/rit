
package model.search;

import java.io.IOException;
import java.util.ArrayList;
import model.stemming.IStemmingStrategy;
import model.stemming.NullStemmingStrategy;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import utils.ReutersConstants;

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
     * The query parser
     */
    protected QueryParser _queryParser;
    
    /**
     * The stemming algorithm strategy for the query parsing
     */
    protected IStemmingStrategy stemmer;
    
    /**
     * The path where the inverted index is.
     */
    protected String _indexPath;
    
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
        
        this._queryParser = new QueryParser(ReutersConstants.CONTENT, this.stemmer.getAnalyzer());
    }
    
    public AbstractSearcher()
    {
        this.stemmer = new NullStemmingStrategy();
        
        this._queryParser = new QueryParser(ReutersConstants.CONTENT, this.stemmer.getAnalyzer());
       
    }
    
    /**
     * Process the term to get the stemmed one.
     * @param pTerm The term to be stemmed
     * @return the stemmed term according to the stemming algorithm
     */
    public abstract String stemmedWord(String pTerm);
    
    /**
     * Process a search query
     * @param query the content to be looked for
     * @return The list of all documents found
     */
    public abstract ArrayList<String> search(String query);
            
    /**
     *
     * @param stemmer The algorithm strategy
     */
    public void setStemmer(IStemmingStrategy stemmer)
    {
        this.stemmer = stemmer;
        this._queryParser = new QueryParser(ReutersConstants.CONTENT, this.stemmer.getAnalyzer());
    }

    /**
     *
     * @return The index seacher
     */
    public IndexSearcher getIndexSearcher()
    {
        return _indexSearcher;
    }

    public String getIndexPath() 
    {
        return _indexPath;
    }

    public void setIndexPath(String _indexPath) 
    {
        this._indexPath = _indexPath;
    }
    
    
    
}
