package com.example.jobrec.external;

import com.example.jobrec.Entity.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class SerpAPIClient {

    private static final String URL_TEMPLATE = "https://serpapi.com/search.json?engine=google_jobs&q=%s&hl=en&api_key=%s";  //%s:String 占位符


    private static final String API_KEY = "e29a81f9445063ac8f61988e8cba9518ea57b7fc885d9a1c87b919b29557cd50";


    private static final String DEFAULT_KEYWORD = "developer";


    private static final String DEFAULT_LOCATION = "seattle";


    public List<Item> search(String keyword, String location) {
        if (keyword == null) {
            keyword = DEFAULT_KEYWORD;
        } else if (location == null) {
            location = DEFAULT_LOCATION;
        }


        // “hello world” => “hello%20world”
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
            location = URLEncoder.encode(location, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String url = String.format(URL_TEMPLATE, keyword, location);
        CloseableHttpClient httpclient = HttpClients.createDefault();


        // Create a custom response handler
        ResponseHandler<List<Item>> responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 200) {
                return Collections.emptyList();
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return Collections.emptyList();
            }
            ObjectMapper mapper = new ObjectMapper();
            // Updated return
            List<Item> items =
                    Arrays.asList(mapper.readValue(entity.getContent(), Item[].class));
            extractKeywords(items);
            return items;
        };


        try {
            return httpclient.execute(new HttpGet(url), responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }



    private static void extractKeywords(List<Item> items) {
        EdenAIClient client = new EdenAIClient();
        for (Item item: items) {
            String article = item.getDescription().replace(".", " ");
            Set<String> keywords = new HashSet<>();
            keywords.addAll(client.extract(article, 3));
            keywords.addAll(item.getKeywords());
            item.setKeywords(keywords);
        }
    }


}
