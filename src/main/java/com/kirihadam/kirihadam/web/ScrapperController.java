package com.kirihadam.kirihadam.web;

import com.kirihadam.kirihadam.StatisticsScrapperAPI;
import com.kirihadam.kirihadam.misc.TextCountPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ScrapperController {

//	private static final String url = "https://jaunt-api.com/";

	@Autowired
	private StatisticsScrapperAPI scrapperAPI;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/scrap")
	public List<TextCountPOJO> getWordCount(@RequestParam String url) throws Exception {

		System.out.println("THIS IS MY URL: " + url);

		List<TextCountPOJO> stringIntegerMap = scrapperAPI.scrapAndComputePage(url);

		return stringIntegerMap;
	}


}
