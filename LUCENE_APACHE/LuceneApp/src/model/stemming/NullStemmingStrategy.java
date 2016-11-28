
package model.stemming;

import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public class NullStemmingStrategy implements IStemmingStrategy
{

    @Override
    public Field applyStemm(String pFieldName, String pText)
    {
        return new TextField(pFieldName,new StringReader(pText));
    }

    @Override
    public Analyzer getAnalyzer() 
    {
        return new StandardAnalyzer();
    }

    
    
}
