
package luceneapp;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.stemming.IStemmingStrategy;
import model.stemming.SimpleStemmingStrategy;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.TextField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.tartarus.snowball.ext.EnglishStemmer;
import org.tartarus.snowball.ext.PorterStemmer;
import utils.LuceneConstants;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public class Test_Stemm
{

    public static void main(String[] args) throws IOException
    {
        
        IStemmingStrategy st = new SimpleStemmingStrategy();
        String str = "COLOMBIA COFFEE REVENUE SHARPLY DOWN IN JAN/FEB";
        utils.mis.getFieldTokens((TextField) st.applyStemm("title", str));
        
        
//        EnglishAnalyzer en_an = new EnglishAnalyzer();
//        QueryParser parser = new QueryParser("title", en_an);
//        try {
//            System.out.println("result: " + parser.parse(str)); //amenit
//        } catch (ParseException ex) {
//            Logger.getLogger(Test_Stemm.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        Test_Stemm s = new Test_Stemm();
       

    }
    
    public void tryStem(String field,String s) throws IOException
    {
        
        EnglishAnalyzer en_an = new EnglishAnalyzer();
        QueryParser parser = new QueryParser("your_field", en_an);
        String str = "COLOMBIA'S COFFEE MARKETING TO BE MORE FLEXIBLE";
        try {
            System.out.println("result: " + parser.parse(str)); //amenit
            
//        EnglishAnalyzer analyzer = new EnglishAnalyzer();
//        try (TokenStream tokenStream = analyzer.tokenStream(field, new StringReader(s)))
//        {
//            TokenStream result = new PorterStemFilter(tokenStream);
//            
//            CharTermAttribute charTermAttribute = result.addAttribute(CharTermAttribute.class);
//            
//            tokenStream.reset();
//            while(tokenStream.incrementToken())
//            {
//                String term = charTermAttribute.toString();
//                System.out.println(term);
//            }
//            tokenStream.end();
//        }
        } catch (ParseException ex) {
            Logger.getLogger(Test_Stemm.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    
}
