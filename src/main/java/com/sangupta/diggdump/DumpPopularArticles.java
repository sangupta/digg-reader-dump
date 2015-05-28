package com.sangupta.diggdump;

import io.airlift.airline.Command;

@Command(name = "popular", description = "Dump digged articles")
public class DumpPopularArticles extends DumpFeed {
	
	@Override
	protected String getFeedUrl() {
		return "http://digg.com/user/" + this.userID + "/items/popular.rss";
	}

}
