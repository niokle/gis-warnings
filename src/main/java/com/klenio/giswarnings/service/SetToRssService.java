package com.klenio.giswarnings.service;

import com.klenio.giswarnings.domain.Info;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class SetToRssService {
    public String getXML(List<Info> list) throws IOException, FeedException {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("Ostrzeżenia GIS");
        feed.setLink("https://www.gov.pl/web/gis/ostrzezenia");
        feed.setDescription("Ostrzeżenia Głównego Inspektora Sanitarnego, np. wycofanie produktów szkodliwych dla zdrowia");

        List entries = new ArrayList();
        feed.setEntries(entries);

        for (Info info : list) {
            SyndEntry entry = new SyndEntryImpl();
            entry.setTitle(info.getDescription());
            entry.setLink(info.getLink());
            SyndContent description = new SyndContentImpl();
            description.setType("text/plain");
            description.setValue(info.getLink());
            entry.setDescription(description);
            entries.add(entry);
        }

        StringWriter writer = new StringWriter();
        new SyndFeedOutput().output(feed, writer);
        return writer.toString();
    }
}
