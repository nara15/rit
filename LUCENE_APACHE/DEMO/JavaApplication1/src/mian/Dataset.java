/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mian;

/*
 * The MIT License
 *
 * Copyright 2016 Thibault Debatty.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Thibault Debatty
 */
public class Dataset extends AbstractDataset<News>{
    
    private final String directory;

    public Dataset(String reutersDir) {
        this.directory = reutersDir;

    }

    static Pattern EXTRACTION_PATTERN = Pattern.compile(
            "<DATE>(.*?)</DATE>.*?<TOPICS>(.*?)</TOPICS>.*?<TITLE>(.*?)</TITLE>.*?<BODY>(.*?)</BODY>");

    public static ArrayList<News> parseString(String reuters_string, String doc)
    {
        ArrayList<News> reuters_feed = new ArrayList<>();
        //Extract the relevant pieces
        Matcher matcher = EXTRACTION_PATTERN.matcher(reuters_string);
        
        while (matcher.find()) 
        {
            News reuters = new News();
            reuters.date = matcher.group(1);
            reuters.topics = matcher.group(2);
            reuters.title = matcher.group(3).replaceAll("&lt;", "<");
            reuters.body = matcher.group(4).replaceAll("&lt;", "<");
            
            reuters.docName = doc;
            
            reuters_feed.add(reuters);
        }
        return reuters_feed;
    }

    @Override
    public Iterator<News> iterator() {

        return new ReutersIterator(directory);
    }

    private static class ReutersIterator implements Iterator<News> {

        private final LinkedList<News> available = new LinkedList<>();
        private final LinkedList<File> files = new LinkedList<>();
        BufferedReader file_reader;
        
        private String curr_file_name = "";

        public ReutersIterator(String dir_name) {
            
            File directory = new File(dir_name);
            files.addAll(Arrays.asList(directory.listFiles((File file) -> file.getName().endsWith(".xml"))));

            // Open first file and read the first element(s)
            openNextFile();
            readNextElements();
        }

        @Override
        public boolean hasNext() {
            return !available.isEmpty();
        }

        @Override
        public News next() {

            News current = available.removeFirst();
            if (available.isEmpty()) {
                readNextElements();
            }

            return current;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported!");
        }

        private void readNextElements()
        {
            StringBuilder buffer = new StringBuilder(1024);

            while (true)
            {
                try 
                {
                    String line = file_reader.readLine();
                    
                    
                    // We reached the end of this file...
                    if (line == null)
                    {
                        file_reader.close();
                        if (!openNextFile())
                        {
                            // No file left
                            return;
                        }

                        // Might trigger an execption if next file is empty..
                        line = file_reader.readLine();
                        
                        
                    }
                   

                    // Read and append lines until we have a complete reuters news
                    if (!line.contains("</REUTERS"))
                    {
                        buffer.append(line); //.append(' ');
                        continue;
                    }
                    
                    available.addAll(parseString(buffer.toString(), this.curr_file_name));

                    if (!available.isEmpty())
                    {
                        return;
                    }
                } 
                catch (IOException ex)
                {
                    Logger.getLogger(Dataset.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private boolean openNextFile() 
        {
            if (files.isEmpty())
            {
                return false;
            }

            try 
            {
                File file = files.removeFirst();
                file_reader = new BufferedReader(new FileReader(file));
                curr_file_name = file.getName();
                
            } 
            catch (FileNotFoundException ex)
            {
                return false;
            }

            return true;
        }
    }
}