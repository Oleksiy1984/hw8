package com.alex.Config;



import com.alex.rss.model.Feed;
import com.alex.rss.model.FeedMessage;
import com.alex.rss.read.RSSFeedParser;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan(basePackages = "com.alex")
@EnableScheduling
public class AppConfig {

//    @Bean
//    public TaskScheduler poolScheduler()
//    {
//        return Executors.newScheduledThreadPool(1).execute();
//    }

    @Bean
    public URL url() throws MalformedURLException {
            return new URL("http://www.pravda.com.ua/rss/view_news/");

    }

//    @Bean
//    //@Scope("prototype")
//    public RSSFeedParser parser() throws MalformedURLException {
//        return new RSSFeedParser(url());}

//    @Bean
//    public Feed feed() throws MalformedURLException {
//        return parser().readFeed();
//    }

    @Scheduled(fixedRate = 10000)
    public void fixedRateJob() throws IOException, FeedException {
        HttpURLConnection httpcon = (HttpURLConnection)url().openConnection();
        // Reading the feed
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(httpcon));
        List entries = feed.getEntries();
        Iterator itEntries = entries.iterator();

        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            System.out.println("Title: " + entry.getTitle());
            System.out.println("Link: " + entry.getLink());
            System.out.println("Publish Date: " + entry.getPublishedDate());
            System.out.println("Description: " + entry.getDescription().getValue());
            System.out.println();
        }
        System.out.println(entries.size());

    }


//    public void fixedRateJob() throws MalformedURLException {
//        List<FeedMessage> queue=new ArrayList<>(parser().readFeed().getMessages());
//        for (int i = 0; i <queue.size() ; i++) {
//            System.out.println(queue.get(i));
//        }
//        System.out.println(queue.size());
//
//    }

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(1);
        return scheduler;
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

//    @Autowired
//    private Feed feed;


//@Bean
//    public List<FeedMessage> doi(){
//        RSSFeedParser parser = new RSSFeedParser(
//                "http://www.pravda.com.ua/rss/view_news/");
//        Feed feed = parser.readFeed();
////        List<FeedMessage> list=new ArrayList<>(feed.getMessages());
////        System.out.println(list.get(0));
//
//       return feed.getMessages();
//    }

//    @Bean
//    public WriteToFile cacheWriter(){
//        return new CacheWriter(3,new Date(),dateFormat(),"file.txt");
//    }


}
