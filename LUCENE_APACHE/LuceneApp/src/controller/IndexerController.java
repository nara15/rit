
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
    private String _indexPath = "C://RIT//index";

    public IndexerController()
    {
        
    }

    public String getIndexPath()
    {
        return _indexPath;
    }

    public void setIndexPath(String _indexPath)
    {
        this._indexPath = _indexPath;
    }
    
    /**
     * This method controls the index creation
     * @param pFrame The frame
     */
    public void controllIndexCreation(JFrame pFrame)
    {
        MainWindow frame = (MainWindow) pFrame;
 
        LinkedList files = new LinkedList(Arrays.asList(frame.getSelectedFiles()));
        
        if (files.size() != 0)
        {
            try
            {
                System.out.println(files.size());
                
                this._i = new Reuters_Indexer(this._indexPath);
                
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
