package luceneapp;

import java.io.IOException;
import model.dataset.Dataset;
import model.indexer.Reuters_Indexer;
import model.stemming.SimpleStemmingStrategy;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public class LuceneApp 
{

    public static void main(String[] args) throws IOException
    {
        Reuters_Indexer i;
        i = new Reuters_Indexer("C://RIT//index");

        Dataset reuters_dataset;
        reuters_dataset = new Dataset("C:\\RIT\\Reuters21578\\11\\");

        i.setStemmer(new SimpleStemmingStrategy());

        i.createIndex(reuters_dataset);
        
        
        
        i.close();
        
    }
}
