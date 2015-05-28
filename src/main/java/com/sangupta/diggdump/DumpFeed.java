package com.sangupta.diggdump;

import com.google.gson.FieldNamingPolicy;
import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.jerry.util.UriUtils;

public abstract class DumpFeed extends DiggDumpCommand {
	
	protected abstract String getFeedUrl();

	@Override
	public void execute() {
		String nextPosition = null;
		
		do {
			String url = "http://digg.com/api/reader/feed.json?feed_url=" + UriUtils.encodeURIComponent(getFeedUrl()) + "&count=50&show=all";
			if(nextPosition != null) {
				url += "&position=" + nextPosition;
			}
			
			WebRequest request = WebRequest.get(url);
			massageUrlForAuthorization(request);
			WebResponse response = WebInvoker.executeSilently(request);
			if(response == null) {
				System.out.println("Unable to hit the internet to fetch subscriptions.");
				return;
			}
			
			if(!response.isSuccess()) {
				System.out.print("Non-success response from the server: ");
				System.out.println(response.trace());
				return;
			}
			
			String json = response.getContent();
			SubscriptionResult result = GsonUtils.getGson(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).fromJson(response.getContent(), SubscriptionResult.class);
			if(result == null) {
				System.out.println("Response is misunderstood, contact the developer!");
				return;
			}
			
			if(result.data == null || result.data.feed == null || result.data.feed.length == 0) {
				System.out.println("There are no feeds in your subscriptions.");
				return;
			}
			
			// start iterating and show the data on screen
			for(Feed feed : result.data.feed) {
				System.out.println(feed.publishDate + ": " + feed.content.title);
			}
			
			nextPosition = result.data.nextPosition;
		} while(true);
	}

	private static class SubscriptionResult {
		
		String status;
		
		String mesg;
		
		FeedData data;
		
	}
	
	private static class FeedData {
		
		Feed[] feed;
		
		String nextPosition;
	}
	
	private static class Feed {
		
		int dots;
		
		long date;
		
		double diggScore;
		
		boolean dugg;
		
		boolean read;
		
		String storyId;
		
		boolean readLater;
		
		boolean disableToggleRead;
		
		double score;
		
		long publishDate;
		
		Count tweets;
		
		Count fbShares;
		
		boolean keepUnread;
		
		Content content;
		
	}
	
	private static class Count {
		
		long count;
		
	}

	private static class Content {
		
		String domain;
		
		String titleAlt;
		
		boolean ignoreImageOnWeb;
		
		boolean showAuthorBios;
		
		String feedUrl;
		
		String title;
		
		Media media;
		
		String domainName;
		
		String body;
		
		String description;
		
		String feedTitle;
		
		boolean sponsored;
		
		boolean featuredOriginal;
		
		boolean marquee;
		
		String url;
		
		boolean published;
		
		boolean forceWebView;
		
		String contentId;
		
	}
	
	private static class Media {
		
		Image[] images;
		
		Audio[] audio;
		
		Video[] videos;
	}
	
	private static class Image {
		
	}
	
	private static class Video {
		
	}
	
	private static class Audio {
		
	}
	
}
