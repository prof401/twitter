package net.april1.twitter;

import java.util.Date;
import java.util.List;

import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.User;

public class MyUser implements User {

	private static final long serialVersionUID = 1L;
	private User user;
	private boolean following;
	private List<String> lists = new java.util.ArrayList<String>();

	public MyUser(User baseUser) {
		user = baseUser;
	}

	@Override
	public int compareTo(User arg0) {
		return user.compareTo(arg0);
	}

	@Override
	public int getAccessLevel() {
		return user.getAccessLevel();
	}

	@Override
	public RateLimitStatus getRateLimitStatus() {
		return user.getRateLimitStatus();
	}

	@Override
	public String getBiggerProfileImageURL() {
		return user.getBiggerProfileImageURL();
	}

	@Override
	public String getBiggerProfileImageURLHttps() {
		return user.getBiggerProfileImageURLHttps();
	}

	@Override
	public Date getCreatedAt() {
		return user.getCreatedAt();
	}

	@Override
	public String getDescription() {
		return user.getDescription();
	}

	@Override
	public URLEntity[] getDescriptionURLEntities() {
		return user.getDescriptionURLEntities();
	}

	@Override
	public int getFavouritesCount() {
		return user.getFavouritesCount();
	}

	@Override
	public int getFollowersCount() {
		return user.getFollowersCount();
	}

	@Override
	public int getFriendsCount() {
		return user.getFriendsCount();
	}

	@Override
	public long getId() {
		return user.getId();
	}

	@Override
	public String getLang() {
		return user.getLang();
	}

	@Override
	public int getListedCount() {
		return user.getListedCount();
	}

	@Override
	public String getLocation() {
		return user.getLocation();
	}

	@Override
	public String getMiniProfileImageURL() {
		return user.getMiniProfileImageURL();
	}

	@Override
	public String getMiniProfileImageURLHttps() {
		return user.getMiniProfileImageURLHttps();
	}

	@Override
	public String getName() {
		return user.getName();
	}

	@Override
	public String getOriginalProfileImageURL() {
		return user.getOriginalProfileImageURL();
	}

	@Override
	public String getOriginalProfileImageURLHttps() {
		return user.getOriginalProfileImageURLHttps();
	}

	@Override
	public String getProfileBackgroundColor() {
		return user.getProfileBackgroundColor();
	}

	@Override
	public String getProfileBackgroundImageURL() {
		return user.getProfileBackgroundImageURL();
	}

	@Override
	public String getProfileBackgroundImageUrlHttps() {
		return user.getProfileBackgroundImageURL();
	}

	@Override
	public String getProfileBannerIPadRetinaURL() {
		return user.getProfileBannerIPadRetinaURL();
	}

	@Override
	public String getProfileBannerIPadURL() {
		return user.getProfileBannerIPadURL();
	}

	@Override
	public String getProfileBannerMobileRetinaURL() {
		return user.getProfileBannerMobileRetinaURL();
	}

	@Override
	public String getProfileBannerMobileURL() {
		return user.getProfileBannerMobileURL();
	}

	@Override
	public String getProfileBannerRetinaURL() {
		return user.getProfileBannerRetinaURL();
	}

	@Override
	public String getProfileBannerURL() {
		return user.getProfileBannerURL();
	}

	@Override
	public String getProfileImageURL() {
		return user.getProfileImageURL();
	}

	@Override
	public String getProfileImageURLHttps() {
		return user.getProfileImageURLHttps();
	}

	@Override
	public String getProfileLinkColor() {
		return user.getProfileLinkColor();
	}

	@Override
	public String getProfileSidebarBorderColor() {
		return user.getProfileSidebarBorderColor();
	}

	@Override
	public String getProfileSidebarFillColor() {
		return user.getProfileSidebarFillColor();
	}

	@Override
	public String getProfileTextColor() {
		return user.getProfileTextColor();
	}

	@Override
	public String getScreenName() {
		return user.getScreenName();
	}

	@Override
	public Status getStatus() {
		return user.getStatus();
	}

	@Override
	public int getStatusesCount() {
		return user.getStatusesCount();
	}

	@Override
	public String getTimeZone() {
		return user.getTimeZone();
	}

	@Override
	public String getURL() {
		return user.getURL();
	}

	@Override
	public URLEntity getURLEntity() {
		return user.getURLEntity();
	}

	@Override
	public int getUtcOffset() {
		return user.getUtcOffset();
	}

	@Override
	public boolean isContributorsEnabled() {
		return user.isContributorsEnabled();
	}

	@Override
	public boolean isDefaultProfile() {
		return user.isDefaultProfile();
	}

	@Override
	public boolean isDefaultProfileImage() {
		return user.isDefaultProfileImage();
	}

	@Override
	public boolean isFollowRequestSent() {
		return user.isFollowRequestSent();
	}

	@Override
	public boolean isGeoEnabled() {
		return user.isGeoEnabled();
	}

	@Override
	public boolean isProfileBackgroundTiled() {
		return user.isProfileBackgroundTiled();
	}

	@Override
	public boolean isProfileUseBackgroundImage() {
		return user.isProfileUseBackgroundImage();
	}

	@Override
	public boolean isProtected() {
		return user.isProtected();
	}

	@Override
	public boolean isShowAllInlineMedia() {
		return user.isShowAllInlineMedia();
	}

	@Override
	public boolean isTranslator() {
		return user.isTranslator();
	}

	@Override
	public boolean isVerified() {
		return user.isVerified();
	}

	public boolean isFollowing() {
		return following;
	}

	public void setFollowing(boolean following) {
		this.following = following;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		sb.append(user.getId());
		sb.append("\",\"");
		sb.append(user.getScreenName());
		sb.append("\",\"");
		sb.append(user.getName());
		sb.append("\",\"");
		sb.append(user.getCreatedAt());
		sb.append("\",\"");
		sb.append(user.getDescription().replaceAll("\"", "\\\"")
				.replaceAll("[\n\r]", " "));
		sb.append("\",\"");
		sb.append(user.getFavouritesCount());
		sb.append("\",\"");
		sb.append(user.getFollowersCount());
		sb.append("\",\"");
		sb.append(user.getFriendsCount());
		sb.append("\",\"");
		sb.append(user.getLocation());
		sb.append("\",\"");
		sb.append(user.getStatusesCount());
		sb.append("\",\"");
		sb.append(user.getTimeZone());
		sb.append("\",\"");
		sb.append(user.isFollowRequestSent());
		sb.append("\",\"");
		sb.append(user.isGeoEnabled());
		sb.append("\",\"");
		sb.append(user.isProtected());
		sb.append("\",\"");
		sb.append(user.isVerified());
		sb.append("\",\"");
		sb.append(following);
		sb.append("\",\"[");
		boolean needColon = false;
		for(String list : lists) {
			if (needColon) 
				sb.append(":");
			else 
				needColon = true;
			sb.append(list);
		}
		sb.append("]\",\"");
		if (user.getStatus() == null) {
			sb.append("\",\"\"");
		} else {
			sb.append(user.getStatus().getCreatedAt());
			sb.append("\",\"");
			String text = user.getStatus().getText().replaceAll("\"", "\\\"")
					.replaceAll("[\n\r]", " ");
			sb.append(text);
			sb.append("\"");
		}
		return sb.toString();
	}

	public static String headerString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		sb.append("Id");
		sb.append("\",\"");
		sb.append("ScreenName");
		sb.append("\",\"");
		sb.append("Name");
		sb.append("\",\"");
		sb.append("CreatedAt");
		sb.append("\",\"");
		sb.append("Description");
		sb.append("\",\"");
		sb.append("FavouritesCount");
		sb.append("\",\"");
		sb.append("FollowersCount");
		sb.append("\",\"");
		sb.append("FriendsCount");
		sb.append("\",\"");
		sb.append("Location");
		sb.append("\",\"");
		sb.append("StatusesCount");
		sb.append("\",\"");
		sb.append("TimeZone");
		sb.append("\",\"");
		sb.append("FollowRequestSent");
		sb.append("\",\"");
		sb.append("GeoEnabled");
		sb.append("\",\"");
		sb.append("Protected");
		sb.append("\",\"");
		sb.append("Verified");
		sb.append("\",\"");
		sb.append("follow");
		sb.append("\",\"");
		sb.append("list");
		sb.append("\",\"");
		sb.append("Status().getCreatedAt");
		sb.append("\",\"");
		sb.append("Status().getText");
		sb.append("\"");

		return sb.toString();
	}

	public List<String> getLists() {
		return lists;
	}

	public void addList(String list) {
		lists.add(list);
	}
}
