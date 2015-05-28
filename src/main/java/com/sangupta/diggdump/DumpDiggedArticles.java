package com.sangupta.diggdump;

import io.airlift.airline.Command;

@Command(name = "diggs", description = "Dump digged articles")
public class DumpDiggedArticles extends DumpFeed {
	
	@Override
	protected String getFeedUrl() {
		return "http://digg.com/user/" + this.userID + "/diggs.rss";
	}

}
