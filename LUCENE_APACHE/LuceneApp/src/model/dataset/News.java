
package model.dataset;

import java.io.Serializable;

/**
 *
 * @author Thibault Debatty
 */
public class News implements Serializable
{
    public String title = "";
    public String date = "";
    public String body = "";
    public String topics = "";
    public String places = "";
    public String people = "";
    public String orgs = "";
    public String exchanges = "";
    public String docName = "";
    public String author = "";
    public int number = 0;

    @Override
    public String toString() {
        return "News{" + "title=" + title + ", docName=" + docName + ", author=" + author + ", number=" + number + '}';
    }
    
    
}