package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import com.data.Product;


public class Scraper {
    private static final String URL = "https://www.scrapingcourse.com/ecommerce/";

    public static List<Product> scrapeProducts(String url){
        List<Product> products = new ArrayList();
        while(url != null){
            try{
                Document doc = Jsoup.connect(url).get();
               
            }
            catch(IOException e){
                System.err.println("Error Fetching" + e.getMessage());
                break;
            }
        }
        return products; 
    }

    public static void main(String[] args) {
        Document doc;

        try {
            doc = Jsoup
                    .connect("https://www.scrapingcourse.com/ecommerce/")
                    .userAgent(
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")
                    .header("Accept-Language", "*")
                    .get();

        } catch (IOException e) {
            throw new RuntimeException(e);
            
        }
        List<Product> lop = new ArrayList<>();
        Elements productElements = doc.select("li.product");

        for (Element e : productElements) {
            Product product = new Product();

            product.setUrl(e.selectFirst("a").attr("href"));
            product.setImage(e.selectFirst("img").attr("src"));
            product.setName(e.selectFirst("h2").text());
            product.setPrice(e.selectFirst("span").text());

            lop.add(product);
        }

        lop.toString();
        System.out.println(lop);

    }
}
