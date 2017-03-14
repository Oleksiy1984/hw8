package com.alex.impl;

import com.alex.interfaces.WriteToFile;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import javafx.application.Application;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Queue;


public class WriterToFileImpl implements WriteToFile {
    private File file;
    private String fileName = "file.txt";
    private Date date;
    private DateFormat df;
    private static URL url;
    private Queue<SyndEntry> entries;

    public WriterToFileImpl(Date date, DateFormat df, String fileName, URL url ) {
        this.date = date;
        this.df = df;
        this.fileName = fileName;
        this.url=url;
    }

    @PostConstruct
    public void init() throws IOException, FeedException {
        System.out.println("In init method");
        this.file = new File(fileName);
        if(!file.canWrite()){
            System.err.println("Cannot write to file");
        }

    }

    public void write(Queue<SyndEntry> q) throws InterruptedException, IOException {
            System.out.println("write to file");
            while (!q.isEmpty()) {
                SyndEntry entry = q.poll();
                FileUtils.write(new File(fileName), df.format(date) + ": " + entry.getTitle()
                        +entry.getLink()+ "\n", true);
            }

        }

    }



