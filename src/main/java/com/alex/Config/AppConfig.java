package com.alex.Config;

import com.alex.impl.CacheWriter;
import com.alex.interfaces.WriteToFile;
import com.alex.rss.model.Feed;
import com.alex.rss.model.FeedMessage;
import com.alex.rss.read.RSSFeedParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.alex")
public class AppConfig {

    @Bean
    public RSSFeedParser parser() {
        return new RSSFeedParser(
                "http://www.pravda.com.ua/rss/view_news/");}
    @Bean
    public Feed feed(){
        return parser().readFeed();
    }
//    @Autowired
//    private FeedMessage feedMessage;
////
//    @Bean
//    public DateFormat dateFormat() {
//        return DateFormat.getDateTimeInstance();
//    }

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
