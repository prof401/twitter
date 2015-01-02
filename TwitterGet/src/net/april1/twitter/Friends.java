package net.april1.twitter;

import java.util.concurrent.ConcurrentMap;

import twitter4j.PagableResponseList;
import twitter4j.TwitterException;
import twitter4j.User;

public class Friends extends TwitterTransaction implements Runnable {

	private static final String DEFAULT_USER = "prof";
	private int membersLimitRemaining = 0;
	private int membersLimitReset = 0;
	private int membersNextReset = 0;
	private ConcurrentMap<String, MyUser> myMap;

	private Boolean running = false;

	public Friends() {
		this(new java.util.concurrent.ConcurrentHashMap<String, MyUser>(1000));
	}

	public Friends(ConcurrentMap<String, MyUser> friendMap) {
		super();
		myMap = friendMap;
	}

	public Boolean isRunning() {
		return running;
	}

	private PagableResponseList<User> getUsers(long c) throws TwitterException {
		int reset = membersNextReset - (int)(System.currentTimeMillis()/1000);
		twitterPause(reset, membersLimitRemaining);
		PagableResponseList<User> users = _twitter.getFriendsList(DEFAULT_USER,
				c);
		membersLimitRemaining = limitRemaining;
		membersLimitReset = limitReset;
		membersNextReset = nextReset;
		return users;
	}

	public void populateMap() {
		long cursor = -1;
		PagableResponseList<User> friends;
		try {
			do {
				friends = getUsers(cursor);
				for (User friend : friends) {
					MyUser myUser = null;
					if (myMap.containsKey(friend.getScreenName())) {
						myUser = myMap.get(friend.getScreenName());
					} else {
						myUser = new MyUser(friend);
						myMap.put(friend.getScreenName(), myUser);
					}
					myUser.setFollowing(true);
				}
			} while ((cursor = friends.getNextCursor()) != 0);
		} catch (TwitterException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		synchronized (running) {
			running = true;
			populateMap();
			running = false;
			running.notifyAll();
		}
	}

	public static void main(String[] args) {

		ConcurrentMap<String, MyUser> myMap = new java.util.concurrent.ConcurrentHashMap<String, MyUser>(
				1000);

		Friends lf = new Friends(myMap);
		lf.run();
		synchronized (lf.isRunning()) {
		}
		for (String screenName : new java.util.TreeSet<String>(myMap.keySet())) {
			MyUser user = myMap.get(screenName);
			System.out.print(screenName);
			System.out.print(" : ");
			boolean needComma = false;
			for (String list : user.getLists()) {
				if (needComma) {
					System.out.print(", ");
				} else {
					needComma = true;
				}
				System.out.print(list);
			}
			System.out.println();
		}
		System.out.println();
	}
}