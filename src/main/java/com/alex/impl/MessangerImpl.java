package com.alex.impl;

import com.alex.interfaces.Messenger;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MessangerImpl implements Messenger {
    public String getMessage(){
        System.out.println("Input your message");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
