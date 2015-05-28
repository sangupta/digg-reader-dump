package com.sangupta.diggdump;

import io.airlift.airline.Command;

import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.util.GsonUtils;

@Command(name = "subs", description = "Dump user subscriptions")
public class DumpSubscriptions extends DiggDumpCommand {
	
	@Override
	public void execute() {
		String url = "http://digg.com/api/subscription/list.json";
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
		
		SubscriptionResult result = GsonUtils.getGson(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).fromJson(response.getContent(), SubscriptionResult.class);
		if(result == null) {
			System.out.println("Response is misunderstood, contact the developer!");
			return;
		}
		
		if(result.data == null || result.data.subscriptions == null || result.data.subscriptions.isEmpty()) {
			System.out.println("There are no feeds in your subscriptions.");
			return;
		}
		
		for(String folderKey : result.data.subscriptions.keySet()) {
			SubscriptionFolder folder = result.data.subscriptions.get(folderKey);
			System.out.println("Subscriptions in folder: ");
			if(folder.subs == null || folder.subs.length == 0) {
				System.out.println("\tNothing in folder.");
				continue;
			}
			
			for(Subscription sub : folder.subs) {
				System.out.println("\t" + sub.title + " :: " + sub.feedUrl);
			}
		}
	}
	
	private static class SubscriptionResult {
		
		String status;
		
		String mesg;
		
		SubscriptionData data;
		
	}
	
	private static class SubscriptionData {
		
		SubscriptionProgress progress;
		
		Map<String, SubscriptionFolder> subscriptions;
		
	}
	
	private static class SubscriptionProgress {
		
		int unreadPollingInterval;
		
		int pollingStatus;
		
		int pollingInterval;
		
	}
	
	private static class SubscriptionFolder {
		
		public Subscription[] subs;
		
		public String show;
		
		public boolean expanded;
		
		public String sortOrder;
		
		public int unread;
		
		public String id;
		
		public String view;
		
	}
	
	private static class Subscription {
		
		public Folder[] folders;
		
		public String feedUrl;
		
		public String show;
		
		public boolean isDead;
		
		public String title;
		
		public String htmlUrl;
		
		public String image;
		
		public String view;
		
		public String subscriptionId;
		
		public int unread;
		
		public long markAsReadDate;
		
		public String icon;
		
	}
	
	private static class Folder {
		
		public String id;
		
		public String label;
		
	}
}
