package com.klenio.giswarnings.service;

import com.klenio.giswarnings.domain.Info;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetToRssService {
    public String getXML(Set<Info> set) throws IOException, FeedException {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("gis warnings feed");
        feed.setLink("http://example.com/");
        feed.setDescription("cos tam");

        List entries = new ArrayList();
        feed.setEntries(entries);

        for (Info info : set) {
            SyndEntry entry = new SyndEntryImpl();
            entry.setTitle(info.getDescription());
            entry.setLink(info.getLink());
            //SyndContent description = new SyndContentImpl();
            //description.setType("text/plain");
            //description.setValue("There is text in here.");
            //entry.setDescription(description);
            entries.add(entry);
        }

        StringWriter writer = new StringWriter();
        new SyndFeedOutput().output(feed, writer);
        return writer.toString();
    }
}
