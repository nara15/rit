
package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainTestRegex {

        private static final String s = "<TITLE>STANDARD OIL <SRD> TO FORM FINANCIAL UNIT</TITLE><DATELINE>    CLEVELAND, Feb 26 - </DATELINE><BODY>Standard Oil Co and BP North AmericaInc said they plan to form a venture to manage the money marketborrowing and investment activities of both companies.    BP North America is a subsidiary of British Petroleum CoPlc <BP>, which also owns a 55 pct interest in Standard Oil.    The venture will be called BP/Standard Financial Tradingand will be operated by Standard Oil under the oversight of ajoint management committee. Reuter </BODY>";
    private static final String regex = "<TITLE>(.*)<\\/TITLE>(?:.*?<AUTHOR>(.*)<\\/AUTHOR>)?(?:.*?<BODY>(.*)<\\/BODY>)?";
//    private static final String s = "<a>hola desde a</a>    <b>desde b</b>      <c>desde C</c>";
//    private static final String regex = "<a>(.*)<\\/a>(?:.*?<b>(.*)<\\/b>)?(?:.*?<c>(.*)<\\/c>)?";
    private static Pattern EXTRACTION_PATTERN;
    private static Matcher matcher;
    
    public static void main(String[] args) 
    {
        EXTRACTION_PATTERN = Pattern.compile(regex);
        matcher = EXTRACTION_PATTERN.matcher(s);
        
       matcher.find();
       System.out.println(matcher.groupCount());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));       
       
     
        
        
        
        
        
       
    }
    
}
