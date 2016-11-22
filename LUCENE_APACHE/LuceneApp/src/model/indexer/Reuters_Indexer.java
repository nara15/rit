
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
import utils.ReutersConstants;

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
    /**
     * This method indexes multivalued field
     * 
     * @param pFieldName The name of the field to be indexed
     * @param pValues A string containing all the values of the for
     * <TAG>value1</TAG>...<TAG>valuen<TAG>
     * @param pDocument The document to which append the created field.
     */
    private void indexMultiValued(String pFieldName, String pValues, Document pDocument)
    {
        String rgx = "<([a-zA-Z]+)>(.*?)<\\/\\1>";
        Pattern extraction = Pattern.compile(rgx);
        Matcher m = extraction.matcher(pValues);
         TextField f; 
        while(m.find())
        {
            f = new TextField(pFieldName, m.group(2),Field.Store.YES);
           
            pDocument.add(f);
        }
    }
    
    /**
     * This indexes a new document
     * @param pFile The new to be indexed
     * @throws IOException
     */
    private void indexFile(News pFile) throws IOException
    {
        Document doc = this.getDocument(pFile);
        this._writer.addDocument(doc);
    }
    
    /**
     * This method returns a document with ts  correspondinf fields.
     * @param pFile The object containing the fields values.
     * @return The document with the field.
     */
    private Document getDocument(News pFile)
    {
        Document document = new Document();
        // The content
        if (pFile.body != null)
        {
            TextField contentField = (TextField) this.stemmer.applyStemm(utils.ReutersConstants.CONTENT, pFile.body);
            document.add(contentField);
        }
        // The author
        if (pFile.author != null)
        {
            TextField authorField = new TextField(utils.ReutersConstants.AUTHOR, new StringReader(pFile.author));
            document.add(authorField);
        }
        // The title
        if (pFile.title != null)
        {
            TextField titleField = (TextField) this.stemmer.applyStemm(utils.ReutersConstants.TITLE, pFile.title);
            document.add(titleField);
        }
        
        // Document Name
        TextField fileNameField = new TextField(LuceneConstants.FILE_NAME, pFile.docName,Field.Store.YES);
        document.add(fileNameField);
        
        // The article number
        String artNumber = Integer.toString(pFile.number);
        TextField n = new TextField(ReutersConstants.ARTICLE_NUMBER, artNumber,Field.Store.YES);
        document.add(n);
        
        // Multivalued fields
        indexMultiValued(ReutersConstants.TOPICS, pFile.topics, document);
        indexMultiValued(ReutersConstants.PLACES, pFile.places, document);
        indexMultiValued(ReutersConstants.PEOPLE, pFile.people, document);
        indexMultiValued(ReutersConstants.EXCH, pFile.exchanges, document);
        indexMultiValued(ReutersConstants.ORGS, pFile.orgs, document);
        
        // The id text
        TextField id = new TextField(ReutersConstants.HEADER, pFile.id_Text, Field.Store.YES);
        document.add(id);
        
        return document;
    }
    
}
