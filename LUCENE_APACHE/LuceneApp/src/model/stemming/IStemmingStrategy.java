
package model.stemming;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Field;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public interface IStemmingStrategy
{

    /**
     * This method creates a new Field appplying to content a stemming algorthm 
     * @param pFieldName The field name
     * @param pText The text to be stemmed.
     * @return a field with the field name and tokens to be indexed.
     */
    public Field applyStemm(String pFieldName, String pText);
    
    /**
     *
     * @return  the used text analyzer
     */
    public Analyzer getAnalyzer();
}
