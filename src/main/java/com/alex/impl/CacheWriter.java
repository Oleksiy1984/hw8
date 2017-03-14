package com.alex.impl;

import com.alex.interfaces.WriteToFile;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.FeedException;
import org.apache.commons.io.FileUtils;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;


public class CacheWriter implements WriteToFile {
    private File file;
    private int cacheSize;
    private Queue<SyndEntry> cache;
    private static DateFormat df;
    private static String fileName;
    private static Date date;



    public CacheWriter(int cacheSize , Date date, DateFormat df, String fileName) {
        this.date=date;
        this.cacheSize = cacheSize;
        this.cache = new ArrayDeque(cacheSize);
        this.df=df;
        this.fileName=fileName;
    }



    @PostConstruct
    public void init() throws IOException, FeedException {
        System.out.println("In init method");
        this.file = new File(fileName);
        if(!file.canWrite()){
            System.err.println("Cannot write to file");
        }
    }

    @Override
    public void write(Queue<SyndEntry> q) throws InterruptedException, IOException {
        while (!q.isEmpty()) {
            for (int i = 0; i < cacheSize; i++) {
                SyndEntry entry = q.poll();
                cache.add(entry);
                System.out.println(q.size()+" "+df.format(new Date()) + " " + entry.getTitle()
                        + " " + entry.getLink());
            }
            Thread.sleep(5000);
        }
        writeToFile(cache);
    }

    public static void writeToFile(Queue<SyndEntry> q) throws InterruptedException, IOException {
        System.out.println("write to file");
        while (!q.isEmpty()) {
            SyndEntry entry = q.poll();
            FileUtils.write(new File(fileName), df.format(new Date()) + " " + entry.getTitle()
                  +" "  +entry.getLink()+ "\n", true);
        }
    }

}
