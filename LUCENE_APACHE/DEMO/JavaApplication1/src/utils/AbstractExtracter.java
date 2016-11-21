
package utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author José Mario Naranjo Leiva
 */
public abstract class AbstractExtracter
{
    protected Pattern EXTRACTION_PATTERN;
    protected Matcher matcher;
    
    public void setRegex(String regex)
    {
        EXTRACTION_PATTERN = Pattern.compile(regex);
    }
  
    /**
     * This initializes the regex processor and matcher.
     * @param pattern Regex to be compiled.
     * @param text Text to used by the matcher to find the pattern.
     */
    protected void initPattern(String pattern, String text)
    {
        EXTRACTION_PATTERN = Pattern.compile(pattern);
        matcher = EXTRACTION_PATTERN.matcher(text);
    }
    
    public abstract Object test(String pExtractPattern, String... pGroupNames);
    
    /**
     * This method is intended for extracting simple data from a string and 
     * is stored in class container for further use. 
     * @param pText Text to extract data from.
     * @return The data found in text.
     */
    public abstract String extractContent(String pText);
    
    /**
     * This method is for extracting nested info of the form <a>data</a>...<a>data</a>.
     * Data is contained within common tags and so data must be treated equally.
     * @param pExtractPattern Pattern or regex to extract text.
     * @param pText Text to extract data from.
     * @return Array list with the extracted data.
     */
    public abstract ArrayList<String> extractNestedContent(String pExtractPattern, String pText);
}
