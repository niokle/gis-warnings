package com.klenio.giswarnings.controller;

import com.klenio.giswarnings.configuration.Config;
import com.klenio.giswarnings.service.SetToRssService;
import com.klenio.giswarnings.service.UrlContentToSetService;
import com.rometools.rome.io.FeedException;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class RssController {
    @GetMapping("/rss")
    @ResponseBody
    public String getRss() throws IOException, FeedException {
        //todo nie działa
        Config config = new Config();
        String url = "https://www.gov.pl";

        UrlContentToSetService urlContentToSetService = new UrlContentToSetService();
        SetToRssService setToRssService = new SetToRssService();

        return setToRssService.getXML(urlContentToSetService.getInfoList(url + "/web/gis/ostrzezenia"));
      }
}
