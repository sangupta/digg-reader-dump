package com.sangupta.diggdump;

import java.io.File;
import java.io.IOException;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.http.service.HttpService;
import com.sangupta.jerry.http.service.impl.DefaultHttpServiceImpl;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.FileUtils;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.jerry.util.UriUtils;

public abstract class DumpFeed extends DiggDumpCommand {
	
	protected abstract String getFeedUrl();
	
	protected abstract String operation();
	
	private final HttpService httpService = new DefaultHttpServiceImpl();

	private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
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
			WebResponse response = this.httpService.executeSilently(request);
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
			
			if(result.data == null || result.data.feed == null || AssertUtils.isEmpty(result.data.nextPosition) || "-1".equals(result.data.nextPosition)) {
				System.out.println("\n\nThere are no more feed items in your subscriptions to export!");
//				System.out.println("\n\n" + "Server response was: \n" + json + "\n\n");
				return;
			}
			
			// start iterating and show the data on screen
			File parent = FileUtils.resolveToFile("./" + this.operation());
			try {
				org.apache.commons.io.FileUtils.forceMkdir(parent);
			} catch (IOException e1) {
				System.out.println("Unable to create folder to save all dump data... exiting!");
				return;
			}
			
			for(Feed feed : result.data.feed) {
				String filename = feed.content.contentId + ".json";
				try {
					org.apache.commons.io.FileUtils.write(new File(parent, filename), GSON.toJson(feed));
				} catch (IOException e) {
					System.out.println("Unable to save contents for article: " + feed.content.feedUrl);
					return;
				}
				
				System.out.println("Saved article (" + filename + "): " + feed.content.title);
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
