package com.alex.impl;

import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.*;

@Component
public class CacheWriter extends WriterToFileImpl {
    private int cacheSize;
    private Queue<SyndEntry> cache;
    private DateFormat df;


    public CacheWriter(int cacheSize , Date date, DateFormat df, String fileName,URL url) {
        super(date, df, fileName,url);
        this.cacheSize = cacheSize;
        this.cache = new ArrayDeque(cacheSize);
        this.df=df;
    }
    @PreDestroy
    public void destroy() throws InterruptedException, IOException {
        System.out.println("DESTROY method");
        if ( ! cache.isEmpty()) {
           super.write(cache);
        }
    }

    @Override
    public void write(Queue<SyndEntry> q) throws InterruptedException {
        while (!q.isEmpty()) {
            for (int i = 0; i < cacheSize; i++) {
                SyndEntry entry = q.poll();
                cache.add(entry);
                System.out.println(q.size()+" "+df.format(new Date()) + " " + entry.getTitle()
                        + " " + entry.getLink());
            }
            Thread.sleep(1000);
        }
    }

}
