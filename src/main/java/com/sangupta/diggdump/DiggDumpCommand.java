package com.sangupta.diggdump;

import io.airlift.airline.Option;

import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.util.AssertUtils;

public abstract class DiggDumpCommand implements Runnable {

	@Option(name = { "--cookies", "-c" }, description = "The cookies to be sent while accessing Digg Reader")
	protected String authKey;
	
	protected void massageUrlForAuthorization(WebRequest request) {
		if(AssertUtils.isEmpty(request)) {
			throw new IllegalArgumentException("WebRequest cannot be empty/null");
		}

		request.addHeader("Cookie", this.authKey);
		request.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.65 Safari/537.36");
	}
}
