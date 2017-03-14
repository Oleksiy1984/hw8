package com.alex;

import com.alex.Config.AppConfig;
import com.alex.rss.model.Feed;
import com.alex.rss.model.FeedMessage;
import com.alex.rss.read.RSSFeedParser;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */

public class App
{

    public static void main( String[] args ) throws InterruptedException {


        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);


//        Feed feed = context.getBean(Feed.class);
//        for (FeedMessage message : feed.getMessages()) {
//            System.out.println(message);
//
//
//        }
//        Messenger messenger=context.getBean("messangerImpl",Messenger.class);
//        WriteToFile writeToFile=context.getBean("cacheWriter",WriteToFile.class);
//
//        int i = 0;
//        while (i < 5){
//            writeToFile.write(messenger.getMessage());
//            i++;
//        }
//
//        for (String name:context.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
        //context.close();


//        RSSFeedParser parser = new RSSFeedParser(
//                "http://www.pravda.com.ua/rss/view_news/");
//        Feed feed = parser.readFeed();
////        List<FeedMessage> list=new ArrayList<>(feed.getMessages());
////        System.out.println(list.get(0));
//        for (FeedMessage message : feed.getMessages()) {
//            System.out.println(message);
//
//        }
    }}

