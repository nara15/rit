
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jose Mario
 */
public class LogController 
{
    public static void writeOnLog(String content)
    {
        String dir = System.getProperty("user.home") + "/Documents/";
        System.out.println(dir);
       try 
       {
            File file = new File(dir+"lucene_log.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) 
            {
                    file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");
       }
        catch (IOException e)
        {
            e.printStackTrace();
	}

    }
}
