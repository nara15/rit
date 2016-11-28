
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import model.search.AbstractSearcher;
import model.search.Reuters_Searcher;
import model.stemming.SimpleStemmingStrategy;
import view.MainWindow;

/**
 *
 * @author Jose Mario
 */
public class SearchController
{
    private AbstractSearcher searcher;
    private String _indexPath = "C://RIT//index";

    public SearchController() 
    {
        try 
        {
            searcher = new Reuters_Searcher(_indexPath);
            searcher.setStemmer(new SimpleStemmingStrategy());
        } 
        catch (IOException ex) 
        {
            //Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void create()
    {
        try 
        {
            searcher = new Reuters_Searcher(_indexPath);
            searcher.setStemmer(new SimpleStemmingStrategy());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getIndexPath()
    {
        return _indexPath;
    }

    public void setIndexPath(String _indexPath)
    {
        this._indexPath = _indexPath;
    }
    
    public void controlSearch(JFrame pFrame, String query)
    {
        this.create();
        
        MainWindow frame = (MainWindow) pFrame;
        
        ArrayList<String> docs = this.searcher.search(query);
        
        String rank = "";
        
        if (!docs.isEmpty())
        {
            frame.getTxtResultArea().setText(" ");
            rank+= docs.size() + " NOTICIAS RECUPERADAS\n";
            
            rank = docs.stream().map((doc) -> doc + "\n").reduce(rank, String::concat);
            frame.getTxtResultArea().setText(rank);
            
            LogController.writeOnLog(query+"\n" + rank + "*********************************************************************\n\n");
        }
        else
        {
            frame.getTxtResultArea().setText(" ");
            rank+= "0 NOTICIAS RECUPERADAS\n";
            frame.getTxtResultArea().setText(rank);
        }
       
        System.out.println(docs.size() + " hits");
     
    }
    
}
