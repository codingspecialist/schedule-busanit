package com.bitc.practiceProgress.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupTest {
	public static void main(String[] args) {
		
		URL url = null;
		try {
			url = new URL("https://www.monamimall.com/w/product/productList.do?schCateIdx1=184&schCateIdx2=565");
			Document doc = Jsoup.parse(url, 2000);
//			
//			System.out.println(doc.toString());
			
			Elements lis = doc.select(".thumb-img");
			
			System.out.println(lis.size());
			
			Elements imgs = lis.select("img");
			
			System.out.println(imgs.get(0).attr("src"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
