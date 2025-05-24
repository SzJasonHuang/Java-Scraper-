package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import com.data.Product;


public class Scraper {
    //private static final String URL = "https://www.scrapingcourse.com/ecommerce/";
    private static final String URL1 = "https://www.scrapingcourse.com/ecommerce/";
    private static final String URL2 = "https://www.uniqlo.com/us/en/men/tops/t-shirts?path=%2C%2C23386%2C";

    public static List<Product> scrapeProducts(String url){
        List<Product> products = new ArrayList<>();
        while(url != null){
            try{
                Document doc = Jsoup.connect(url).get();
                Element productElements = doc.selectFirst("li.product");
                for (Element productElement : productElements){
                    Product product = new Product();
                    // extracting product details safely
                   Element linkElement = productElement.selectFirst(".woocommerce-LoopProduct-link");
                   Element imgElement = productElement.selectFirst(".product-image");
                   Element nameElement = productElement.selectFirst(".product-name");
                   Element priceElement = productElement.selectFirst(".price");

                   product.setUrl(linkElement != null ? linkElement.attr("href") : "N/A");
                   product.setImage(imgElement != null ? imgElement.attr("src") : "N/A");
                   product.setName(nameElement != null ? nameElement.text() : "N/A");
                   product.setPrice(priceElement != null ? priceElement.text() : "N/A");

                   // add the product to the list
                   products.add(product);
               }
               Element nextButton = doc.selectFirst("a.next");
               if (nextButton != null) {
                   String nextPageUrl = nextButton.attr("href");
                   if (!nextPageUrl.startsWith("http")) {
                       nextPageUrl = url + nextPageUrl.replaceFirst("^/", "");
                   }
                   url = nextPageUrl; // update URL for next iteration
               } else {
                   url = null; // no more pages, exit loop
               }
                }
            catch(IOException e){
                System.err.println("Error Fetching" + e.getMessage());
                break;
            }
        }
        return products; 
    }

    public static void main(String[] args) {
        List<Product> products = scrapeProducts(URL1);
        System.out.println(products.toString());
    }
}
