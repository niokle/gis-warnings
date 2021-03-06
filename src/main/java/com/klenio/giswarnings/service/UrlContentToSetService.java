package com.klenio.giswarnings.service;

import com.klenio.giswarnings.configuration.Config;
import com.klenio.giswarnings.domain.Info;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@NoArgsConstructor
public class UrlContentToSetService {
    @Autowired
    private Config config;

    public List<Info> getInfoList(String url) {
        Set<Info> resultSet = new HashSet<>();
        List<Info> resultList = new ArrayList<>();
        int resultSize = 0;
        int pageNo = 0;
        String date = "";
        String desc = "";
        String link = "";
        do {
            resultSize = resultSet.size();
            pageNo++;
            for (Element element : getContentElements(url + "?page=" + pageNo)) {
                date = element.select("span.date").text().isEmpty() ? "01.01.0001" : element.select("span.date").text();
                desc = element.text();
                link = config.getBaseUrl() + element.attr("href");
                resultSet.add(new Info(date, desc, link));
                resultList.add(new Info(date, desc, link));
            }
        } while (resultSize != resultSet.size());
        return resultList;
    }

    private Elements getContentElements(String url) {
        Document documents = Jsoup.parse(readFromUrlToString(url));
        Elements elements = documents.select("div.art-prev").select("a");
        return elements;
    }

    private String readFromUrlToString(String url) {
        String fileContents = "";
        String currentLine = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            fileContents = reader.readLine();
            while (currentLine != null) {
                currentLine = reader.readLine();
                fileContents += "\n" + currentLine;
            }
            reader.close();
            reader = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Message", JOptionPane.OK_OPTION);
            e.printStackTrace();
        }
        return fileContents;
    }
}
