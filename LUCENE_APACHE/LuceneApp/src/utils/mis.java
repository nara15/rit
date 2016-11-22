
package utils;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.TextField;

/**
 * Miscelanious utitlities
 * @author Jose Mario Naranjo Leiva
 */
public class mis 
{
    public static ArrayList<String> getFieldTokens(TextField f) throws IOException
    {
        ArrayList<String> tokens = new ArrayList<>();
        
        CharTermAttribute charTermAttribute = f.tokenStreamValue().addAttribute(CharTermAttribute.class);
        f.tokenStreamValue().reset();
        while(f.tokenStreamValue().incrementToken())
        {
            String term = charTermAttribute.toString();
            tokens.add(term);
            //System.out.println(term);
        }
        f.tokenStreamValue().end();
        
        return tokens;
    }
}
