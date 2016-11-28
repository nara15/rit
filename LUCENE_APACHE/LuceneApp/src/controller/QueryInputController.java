
package controller;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.search.AbstractSearcher;
import model.search.Reuters_Searcher;
import model.stemming.SimpleStemmingStrategy;

/**
 *
 * @author Jose Mario
 */
public class QueryInputController 
{
    private static final String regexPhrase = "([a-zA-Z]+) ?: ?\"([a-zA-Z ]+)\"";
    private static final String regexTerm = "([A-Za-z]+) ?: ?([A-Za-z]+)";
    private final AbstractSearcher searcher;
    
    public QueryInputController()
    {
         searcher = new Reuters_Searcher();
         searcher.setStemmer(new SimpleStemmingStrategy());
    }
    
    private String applyStem_to_Phrase(String phrase)
    {
        String[] arr = phrase.split(" ");
        
        for (int i = 0; i < arr.length; i++)
        {
            String temp = this.searcher.stemmedWord(arr[i]);
            arr[i] = temp;
        }

        return String.join(" ", arr);
    }
     
    private String processTerms(String query)
    {
        Pattern EXTRACTION_PATTERN = Pattern.compile(regexTerm);
        Matcher matcher = EXTRACTION_PATTERN.matcher(query);
        
        StringBuffer sb = new StringBuffer(); 
        while (matcher.find())
        {
            String term = this.searcher.stemmedWord(matcher.group(2));
            matcher.appendReplacement(sb, "$1:" + term);  
        }
        matcher.appendTail(sb);  
        return sb.toString();
    }
    
   
    private String processPhrases(String query)
    {
        Pattern EXTRACTION_PATTERN = Pattern.compile(regexPhrase);
        Matcher matcher = EXTRACTION_PATTERN.matcher(query);
        
        StringBuffer sb = new StringBuffer();  
        while(matcher.find())
        {
            String frase = this.applyStem_to_Phrase(matcher.group(2));
            matcher.appendReplacement(sb, "$1:" + "\""+frase+"\"");  
           
        }
        matcher.appendTail(sb);  
        return this.processTerms(sb.toString());
    }
    
    public String parseQuery(String query)
    {
        String res;
        if (query.contains("\""))
        {
            res = processPhrases(query);
        }
        else
        {
            res = this.processTerms(query);
        }
        System.out.println(res);
        return res;
    }
}
