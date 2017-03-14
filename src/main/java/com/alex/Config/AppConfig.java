package com.alex.Config;


import com.alex.impl.CacheWriter;
import com.alex.interfaces.WriteToFile;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@ComponentScan(basePackages = "com.alex")
@EnableScheduling
public class AppConfig {



    @Bean
    public URL url() throws MalformedURLException {
            return new URL("http://www.pravda.com.ua/rss/view_news/");

    }
    @Bean
    public WriteToFile cacheWriter() throws MalformedURLException {

        return new CacheWriter(5,
                new Date(),dateFormat(),
                "file.txt",url());
    }

    @Bean
    public Queue<SyndEntry> list() throws IOException, FeedException {
        HttpURLConnection httpcon = (HttpURLConnection) url().openConnection();
        // Reading the feed
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(httpcon));
        return new ArrayDeque<SyndEntry>(feed.getEntries());
    }

//    @Scheduled(fixedRate = 5000)
//    public void fixedRateJob() throws IOException, FeedException {
//        HttpURLConnection httpcon = (HttpURLConnection)url().openConnection();
//        // Reading the feed
//        SyndFeedInput input = new SyndFeedInput();
//        SyndFeed feed = input.build(new XmlReader(httpcon));
//        List entries = feed.getEntries();
//        Iterator itEntries = entries.iterator();
//        while (itEntries.hasNext()) {
//            SyndEntry entry = (SyndEntry) itEntries.next();
//            System.out.println(dateFormat().format(new Date())+" " + entry.getTitle()
//                    +" " + entry.getLink());
//        }
//    }

//    @Bean
//    public TaskScheduler poolScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setThreadNamePrefix("poolScheduler");
//        scheduler.setPoolSize(1);
//        return scheduler;
//    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }


}
