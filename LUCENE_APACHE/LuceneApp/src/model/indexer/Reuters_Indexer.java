
package model.indexer;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.dataset.AbstractDataset;
import model.dataset.News;
import model.stemming.NullStemmingStrategy;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import utils.LuceneConstants;

/**
 *
 * @author Jose Mario Naranjo Leva
 */
public class Reuters_Indexer  extends AbstractIndexer implements IDataSetIndexer<News>
{
    // REGEX *******************************************************************
    private static final String regex = "<TITLE>(.*)<\\/TITLE>(?:.*?<AUTHOR>(.*)<\\/AUTHOR>)?(?:.*?<BODY>(.*)<\\/BODY>)?";
    private final Pattern EXTRACTION_PATTERN = Pattern.compile(regex);
    private Matcher matcher;
    //**************************************************************************
    
    public Reuters_Indexer(String pIndexDirectoryPath) throws IOException
    {
        super(pIndexDirectoryPath);
        this.stemmer = new NullStemmingStrategy();
    }

    @Override
    public void close() throws IOException
    {
        this._writer.close();
    }

    @Override
    public int createIndex(AbstractDataset<News> pCollection) 
    {
        int i = 1;
        String text_body_content;
        for (News news : pCollection)
        {
            text_body_content = news.body;
            matcher = EXTRACTION_PATTERN.matcher(text_body_content);
            
            news.number = i;
            
            while(matcher.find())
            {    
                news.title = matcher.group(1);  // Title
                news.author = matcher.group(2); // Author
                news.body = matcher.group(3);   // Body
            }
            
            try 
            {
                indexFile(news);
            } 
            catch (IOException ex)
            {
                Logger.getLogger(Reuters_Indexer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            i++;
        }
        System.out.println(this._writer.numDocs());
        return 0;
    }
    
    private void indexFile(News pFile) throws IOException
    {
        Document doc = this.getDocument(pFile);
        this._writer.addDocument(doc);
    }
    
    private Document getDocument(News pFile)
    {
        Document document = new Document();
        
        if (pFile.body != null)
        {
            //TextField contentField = new TextField(utils.ReutersConstants.CONTENT,new StringReader(pFile.body));
            TextField contentField = (TextField) this.stemmer.applyStemm(utils.ReutersConstants.CONTENT, pFile.body);
            document.add(contentField);
        }
        if (pFile.author != null)
        {
            TextField authorField = new TextField(utils.ReutersConstants.AUTHOR, new StringReader(pFile.author));
            document.add(authorField);
        }
        if (pFile.title != null)
        {
            //TextField titleField = new TextField(utils.ReutersConstants.TITLE, new StringReader(pFile.title));
            TextField titleField = (TextField) this.stemmer.applyStemm(utils.ReutersConstants.TITLE, pFile.title);
            document.add(titleField);
        }
        
        TextField fileNameField = new TextField(LuceneConstants.FILE_NAME, pFile.docName,Field.Store.YES);
        document.add(fileNameField);
        
        String artNumber = Integer.toString(pFile.number);
        TextField n = new TextField(utils.ReutersConstants.ARTICLE_NUMBER, artNumber,Field.Store.YES);
        document.add(n);

        return document;
    }
    
}
