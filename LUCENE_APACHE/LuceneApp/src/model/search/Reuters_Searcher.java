
package model.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import utils.LuceneConstants;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public class Reuters_Searcher extends AbstractSearcher
{

    public Reuters_Searcher(String pIndexDirectoryPath) throws IOException
    {
        super(pIndexDirectoryPath);
    }
    
    public Reuters_Searcher()
    {
        
    }

    @Override
    public String stemmedWord(String pTerm) 
    {
        TextField t = (TextField) this.stemmer.applyStemm("query", pTerm);
        try 
        {
            ArrayList<String> stemmed = utils.mis.getFieldTokens(t);
            
            return stemmed.get(0);
        } 
        catch (IOException ex ) 
        {
            Logger.getLogger(Reuters_Searcher.class.getName()).log(Level.SEVERE, null, ex);
            return pTerm;
        }
        catch( NullPointerException e)
        {
            return pTerm;
        }
    }

    @Override
    public ArrayList<String> search(String query) 
    {
        ArrayList<String> documents = new ArrayList<>();
        String res;
        try 
        {
            Query q = _queryParser.parse(query);
            
            TopDocs docs = this._indexSearcher.search(q, 30);
            
            for (ScoreDoc scoreDoc : docs.scoreDocs)
            {
                Document doc = _indexSearcher.doc(scoreDoc.doc);

                res = "File: " + doc.get(LuceneConstants.FILE_NAME) + " <" + doc.get(utils.ReutersConstants.HEADER);
                
                documents.add(res);
            }
        } 
        catch (ParseException | IOException ex) 
        {
            Logger.getLogger(Reuters_Searcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return documents;
    }
    
}
