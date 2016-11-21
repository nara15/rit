
package model.stemming;

import java.io.StringReader;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public class SimpleStemmingStrategy implements IStemmingStrategy
{

    @Override
    public Field applyStemm(String pFieldName, String pText)
    {
        EnglishAnalyzer analyzer = new EnglishAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream(pFieldName, new StringReader(pText));
        TokenStream result = new PorterStemFilter(tokenStream);
        
        TextField field;
        field = new TextField(pFieldName, result);
        
        return field;
    }
    
}
