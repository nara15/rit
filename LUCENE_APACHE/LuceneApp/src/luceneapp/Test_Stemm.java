
package luceneapp;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.tartarus.snowball.ext.EnglishStemmer;
import org.tartarus.snowball.ext.PorterStemmer;
import utils.LuceneConstants;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public class Test_Stemm
{

    public static void main(String[] args)
    {
        Test_Stemm s = new Test_Stemm();
        
        try 
        {
            s.tryStem("content","COLOMBIA'S COFFEE MARKETING TO BE MORE FLEXIBLE");
            
        } catch (IOException ex) {
            Logger.getLogger(Test_Stemm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tryStem(String field,String s) throws IOException
    {
        
        EnglishAnalyzer analyzer = new EnglishAnalyzer();
        try (TokenStream tokenStream = analyzer.tokenStream(field, new StringReader(s)))
        {
            TokenStream result = new PorterStemFilter(tokenStream);
            
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            
            tokenStream.reset();
            while(tokenStream.incrementToken())
            {
                String term = charTermAttribute.toString();
                System.out.println(term);
            }
            tokenStream.end();
        }
        
    }
    
}
