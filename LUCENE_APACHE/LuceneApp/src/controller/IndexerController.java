
package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import model.dataset.AbstractDataset;
import model.dataset.Dataset;
import model.indexer.Reuters_Indexer;
import model.stemming.SimpleStemmingStrategy;
import view.MainWindow;

/**
 *
 * @author Jose Mario Naranjo leiva
 */
public class IndexerController 
{
    private Reuters_Indexer _i;
    private AbstractDataset _dataSet;

    public IndexerController()
    {
        
    }
    
    public void controllIndexCreation(JFrame pFrame)
    {
        MainWindow frame = (MainWindow) pFrame;
 
        LinkedList files = new LinkedList(Arrays.asList(frame.getSelectedFiles()));
        
        if (files.size() != 0)
        {
            try
            {
                System.out.println(files.size());
                
                this._i = new Reuters_Indexer("C://RIT//index");
                
                this._dataSet =  new Dataset(files);
                _i.setStemmer(new SimpleStemmingStrategy());
                _i.createIndex(_dataSet);
                _i.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(IndexerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
