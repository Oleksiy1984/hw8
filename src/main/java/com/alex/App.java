package com.alex;

import com.alex.Config.AppConfig;
import com.alex.impl.CacheWriter;
import com.alex.interfaces.WriteToFile;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
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

    public static void main( String[] args ) throws InterruptedException, IOException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

//        WriteToFile writeToFile = context.getBean(WriteToFile.class);
//        Queue<SyndEntry> queue = (Queue) context.getBean("list");
//
//        writeToFile.write(queue);

        //context.close();

    }
}

