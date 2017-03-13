package com.alex;

import com.alex.Config.AppConfig;
import com.alex.rss.model.Feed;
import com.alex.rss.model.FeedMessage;
import com.alex.rss.read.RSSFeedParser;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        //RSSFeedParser parser=context.getBean(RSSFeedParser.class);
       // Feed feed=parser.readFeed();

//        for (FeedMessage message : feed.getMessages()) {
//            System.out.println(message);
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
        context.close();


//        RSSFeedParser parser = new RSSFeedParser(
//                "http://www.pravda.com.ua/rss/view_news/");
//        Feed feed = parser.readFeed();
////        List<FeedMessage> list=new ArrayList<>(feed.getMessages());
////        System.out.println(list.get(0));
//        for (FeedMessage message : feed.getMessages()) {
//            System.out.println(message);
//
//        }
    }
}
