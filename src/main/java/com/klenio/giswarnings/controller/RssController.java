package com.klenio.giswarnings.controller;

import com.klenio.giswarnings.configuration.Config;
import com.klenio.giswarnings.service.SetToRssService;
import com.klenio.giswarnings.service.UrlContentToSetService;
import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/v1")
public class RssController {
    @Autowired
    private Config config;

    @Autowired
    private UrlContentToSetService urlContentToSetService;

    @Autowired
    private SetToRssService setToRssService;

    @GetMapping("/rss")
    @ResponseBody
    public String getRss() throws IOException, FeedException, ParseException {
           return setToRssService.getXML(urlContentToSetService.getInfoList(config.getBaseUrl() + "/web/gis/ostrzezenia"));
      }
}
