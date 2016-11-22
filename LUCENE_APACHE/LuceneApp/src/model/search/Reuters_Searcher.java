
package model.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.document.TextField;

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
    
}
