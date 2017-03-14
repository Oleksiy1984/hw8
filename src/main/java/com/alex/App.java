package com.alex;

import com.alex.Config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;



public class App
{

    public static void main( String[] args ) throws InterruptedException, IOException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);


    }
}

