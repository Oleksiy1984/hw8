package com.alex.interfaces;


import com.sun.syndication.feed.synd.SyndEntry;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

public interface WriteToFile {
    void write(Queue<SyndEntry> q) throws InterruptedException, IOException;
}
