package com.sangupta.diggdump;

import io.airlift.airline.Command;

@Command(name = "all", description = "Dump all articles")
public class DumpAllArticles extends DumpFeed {
	
	@Override
	protected String getFeedUrl() {
		return "http://digg.com/user/" + this.userID + "/items/all.rss";
	}

}
