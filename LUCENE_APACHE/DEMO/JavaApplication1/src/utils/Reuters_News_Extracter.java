
package utils;

import java.util.ArrayList;

/**
 *
 * @author José Mario Naranjo Leiva
 */
public class Reuters_News_Extracter extends AbstractExtracter
{

    @Override
    public Object test(String pExtractPattern, String... pGroupNames)
    {
        this.initPattern(pExtractPattern, pGroupNames[0]); 
        System.out.println(matcher.groupCount());      
        matcher.find();
        System.out.println(matcher.group(1));
         
        return matcher.group(1);
    }

    @Override
    public String extractContent(String pText)
    { 
        try
        {
            matcher = EXTRACTION_PATTERN.matcher(pText);
            matcher.find();
            return matcher.group(1);
        }
        catch(java.lang.IllegalStateException e)
        {
            return "";
        }
    }

    @Override
    public ArrayList<String> extractNestedContent(String pExtractPattern, String pText)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
