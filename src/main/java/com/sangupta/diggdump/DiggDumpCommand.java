package com.sangupta.diggdump;

import io.airlift.airline.Option;

import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.util.AssertUtils;

public abstract class DiggDumpCommand implements Runnable {

	@Option(name = { "--secret", "-s" }, description = "The secret to be used while accessing Digg Reader")
	protected String authKey;
	
	protected void massageUrlForAuthorization(WebRequest request) {
		if(AssertUtils.isEmpty(request)) {
			throw new IllegalArgumentException("WebRequest cannot be empty/null");
		}

		request.addHeader("Cookie", "__utma=146621099.1988356693.1410165955.1411728330.1411985056.17; twitter-news-optout=1; __qca=P0-33966875-1428897818024; _xsrf=0bed6831c1664469bbb3507152c19ab5; _chartbeat_uuniq=3; _cb_ls=1; preferred_view=desktop; _ga=GA1.2.1988356693.1410165955; frontend.user=\"eyJ1c2VyX2lkIjogIjAzYzdiMThmODhmYzQ4OTk5OTNjOTQ2ZGJiNmY2NDk5IiwgImdyb3VwcyI6IFsicmVhZGVyIl0sICJ2IjogIjEuNi4wIn0=|1432541180|1f1c4a6b6be4c8984ae8df7edd19131cdff4b4ec\"; _chartbeat2=3vT9iDzJR8UDublbn.1410164084683.1432541178892.0111110011101001");
		request.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.65 Safari/537.36");
	}
}
