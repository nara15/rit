
package main;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import javax.xml.stream.*;
/**
 *
 * @author jonaranjo
 */
public class JavaApplication1 {


    public static void main(String[] args) throws FileNotFoundException, XMLStreamException
    {
//       XMLInputFactory inputFactory = XMLInputFactory.newFactory();
//       
//       InputStream in = new FileInputStream("C:\\Users\\jonaranjo.ESTUDIANTES.002\\Downloads\\Reuters21578\\reut2-000.xml");
//       
//       XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
//       
//       streamReader.nextTag();
//       System.out.println(streamReader.getName());
//       
//              streamReader.nextTag();
//       System.out.println(streamReader.getName());
//       
//              streamReader.nextTag();
//       System.out.println(streamReader.getText());
//       
             
       
//        LinkedList<File> files = new LinkedList<>();
//        
//        File directory = new File("C:\\Users\\jonaranjo.ESTUDIANTES.002\\Downloads\\Reuters21578\\");
//        files.addAll(Arrays.asList(directory.listFiles((File file) -> file.getName().endsWith(".xml"))));
//        
//        System.out.println(files.size());
        
        Dataset reuters_dataset = new Dataset("C:\\RIT\\Reuters21578\\");
        int i = 0;
        for (News news : reuters_dataset)
        {
           //System.out.println(news.title);
            //System.out.println(news.topics + "-----" + news.docName);
            i ++;
//            if (i <= 32) 
//            {
//                System.out.println(i + " " + news.title +" " + news.body);
//                
//            }
//            else
//            {
//                break;
//            }
//            
            if (i == 1001)
            {
                 System.out.println(news.docName + " " + news.title +" " + news.body);
                 break;
            }
           
        }
              
    }
    
}
