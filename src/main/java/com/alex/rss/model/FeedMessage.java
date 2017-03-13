package com.alex.rss.model;

import org.springframework.stereotype.Component;

/*
 * Represents one RSS message
 */
@Component
public class FeedMessage {

    String title;
    String description;
    String link;
    String pubDate;
    String guid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getpubDate() {
        return pubDate;
    }

    public void setpubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", link=" + link + ", pubDate=" + pubDate
                + "]";
    }

}
