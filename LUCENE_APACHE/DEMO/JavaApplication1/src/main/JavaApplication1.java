
package main;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author José Mario Naranjo Leiva
 */
public class JavaApplication1 {


    public static void main(String[] args) throws FileNotFoundException
    {
     
        Dataset reuters_dataset = new Dataset("C:\\RIT\\Reuters21578\\");
        int i = 1;
        
        String text_body_content;
        
        long inicio = System.currentTimeMillis();
        
        // REGEX ***************************************************************
        String regex = "<TITLE>(.*)<\\/TITLE>(?:.*?<AUTHOR>(.*)<\\/AUTHOR>)?(?:.*?<BODY>(.*)<\\/BODY>)?";
        Pattern EXTRACTION_PATTERN = Pattern.compile(regex);
        Matcher matcher;
        //**********************************************************************
        
        for (News news : reuters_dataset)
        {
           
            text_body_content = news.body;
            matcher = EXTRACTION_PATTERN.matcher(text_body_content);
            
            System.out.println( news.docName + "->" + i++);
            while(matcher.find())
            {    
                System.out.println(matcher.group(1)); //Title
                System.out.println(matcher.group(2)); // Author
                System.out.println(matcher.group(3)); // Body
                System.out.println("\n");
            }
    
        }
        
        long fin = System.currentTimeMillis();
        
        System.out.println("Duró: " + (fin - inicio ) + " ms");
              
    }
    
}
