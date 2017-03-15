package com.alex;


import com.alex.interfaces.WriteToFile;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppXML implements Runnable{

    private static WriteToFile writeToFile;
    private static URL url;
    public static void main(String[] args) throws IOException, FeedException, InterruptedException {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");

        url= (URL) context.getBean("url");
        writeToFile= (WriteToFile) context.getBean("cacheWriter");

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        //Load RSS every 50 sec and begin print it every 10 sec
        service.scheduleAtFixedRate(new AppXML() , 0, 50, TimeUnit.SECONDS);

    }


    @Override
    public void run() {
        HttpURLConnection httpcon = null;
        try {
            httpcon = (HttpURLConnection) url.openConnection();
            // Reading the feed
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = null;
            feed = input.build(new XmlReader(httpcon));
            Queue<SyndEntry> queue = new ArrayDeque<>(feed.getEntries());
            writeToFile.write(queue);
        } catch (InterruptedException | FeedException | IOException e) {
            e.printStackTrace();
        }
    }
}
