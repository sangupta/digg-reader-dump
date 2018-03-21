package com.sangupta.diggdump;

import java.util.Map;

import io.airlift.airline.Option;

import com.sangupta.jerry.encoder.Base64Encoder;
import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.io.AdvancedStringReader;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.GsonUtils;

public abstract class DiggDumpCommand implements Runnable {

	@Option(name = { "--cookies", "-c" }, description = "The cookies to be sent while accessing Digg Reader")
	protected String authKey;
	
	protected String userID;
	
	protected void massageUrlForAuthorization(WebRequest request) {
		if(AssertUtils.isEmpty(request)) {
			throw new IllegalArgumentException("WebRequest cannot be empty/null");
		}
		
		request.addHeader("Cookie", this.authKey);
		request.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.65 Safari/537.36");
	}
	
	@Override
	public final void run() {
		if(AssertUtils.isEmpty(this.authKey)) {
			System.out.println("Cookie value must be provided over command line for this to work.");
			return;
		}
		
		extractUser();
		
		execute();
	}
	
	private void extractUser() {
		AdvancedStringReader reader = new AdvancedStringReader(this.authKey);
		
		// decode cookie value
		reader.readTillNext("frontend.user=");
		String frontEndUser = reader.readTillNext('|');
		
		// base64 decode
		byte[] bytes = Base64Encoder.decode(frontEndUser);
		String json = new String(bytes);
		
		// read value from JSON
		@SuppressWarnings("unchecked")
		Map<String, String> map = GsonUtils.getGson().fromJson(json, Map.class);
		
		this.userID = map.get("user_id");
	}

	protected abstract void execute();
}
