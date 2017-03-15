package com.alex.Config;


import com.alex.impl.CacheWriter;
import com.alex.interfaces.WriteToFile;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.context.annotation.*;
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
@ComponentScan(basePackages = "com.alex.impl")
@EnableScheduling
public class AppConfig {

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public URL url() throws MalformedURLException {
            return new URL("http://www.pravda.com.ua/rss/view_news/");
    }

    @Bean
    @Scope("prototype")
    public WriteToFile cacheWriter() throws MalformedURLException {
        return new CacheWriter(5,
                new Date(),dateFormat(),
                "file.txt");
    }

    @Bean
    @Scope("prototype")
    public Queue<SyndEntry> queue() throws IOException, FeedException {
        HttpURLConnection httpcon;
            httpcon = (HttpURLConnection) url().openConnection();
        // Reading the feed
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(httpcon));
        return new ArrayDeque<>(feed.getEntries());
    }

    //Load RSS every 50 sec and begin print it every 10 sec
    @Scheduled(fixedRate = 50000)
    public void fixedRateJob() throws IOException, FeedException, InterruptedException {
        cacheWriter().write(queue());
    }

}
