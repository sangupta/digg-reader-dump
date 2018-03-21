package com.sangupta.diggdump;

import io.airlift.airline.Command;

@Command(name = "saved", description = "Dump saved articles")
public class DumpSavedArticles extends DumpFeed {
	
	@Override
	protected String getFeedUrl() {
		return "http://digg.com/user/" + this.userID + "/saved.rss";
	}

	@Override
	protected String operation() {
		return "saved";
	}

}
