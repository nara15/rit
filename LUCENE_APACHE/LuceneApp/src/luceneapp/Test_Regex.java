/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneapp;

import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.lucene.document.TextField;

/**
 *
 * @author Jose Mario
 */
public class Test_Regex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException 
    {
        String rgx = "<([a-zA-Z]+)>(.*?)<\\/\\1>";
        Pattern extraction = Pattern.compile(rgx);
        Matcher m = extraction.matcher("");
        
        TextField authorField; 
        
        while(m.find())
        {
           
            System.out.println(m.group(2));
            
           
        }
    }
    
}
