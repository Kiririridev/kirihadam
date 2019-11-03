package com.kirihadam.kirihadam.webscrapper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Webscrapper {

	public Webscrapper() {
		Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
	}

	public String scrap(String searchUrl) throws Exception {
		
		WebClient webClient = new WebClient();

		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);

		try {
			HtmlPage page = webClient.getPage(searchUrl);

			String text = page.asText();

			return text;
		} catch(Exception e) {
			throw new Exception("Could not scrap the page");
		}
	}


}
