package net.april1.twitter;

import java.util.Queue;
import java.util.concurrent.ConcurrentMap;

import twitter4j.PagableResponseList;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserList;

public class ListFriends extends TwitterTransaction implements Runnable {

	public static void main(String[] args) {

		ConcurrentMap<String, MyUser> myMap = new java.util.concurrent.ConcurrentHashMap<String, MyUser>(
				1000);

		ListFriends lf = new ListFriends(myMap);
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

	private static final String DEFAULT_USER = "prof";
	private int userListLimitRemaining = 0;
	private int userListLimitReset = 0;
	private int membersLimitRemaining = 0;
	private int membersLimitReset = 0;
	private ConcurrentMap<String, MyUser> myMap;

	private Boolean running = false;

	public ListFriends() {
		this(new java.util.concurrent.ConcurrentHashMap<String, MyUser>(1000));
	}

	public ListFriends(ConcurrentMap<String, MyUser> friendMap) {
		super();
		myMap = friendMap;
	}

	public Queue<User> getQueue() {
		Queue<User> userQueue = new java.util.LinkedList<User>();
		try {
			for (UserList list : getUserLists()) {
				for (User user : getUserListMembers(list)) {
					userQueue.add(user);
				}
			}
		} catch (TwitterException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return userQueue;
	}

	private PagableResponseList<User> getUserListMembers(UserList list)
			throws TwitterException {
		twitterPause(membersLimitReset, membersLimitRemaining);
		PagableResponseList<User> users = _twitter.getUserListMembers(
				DEFAULT_USER, list.getSlug(), -1);
		membersLimitRemaining = limitRemaining;
		membersLimitReset = limitReset;
		return users;
	}

	private PagableResponseList<UserList> getUserLists()
			throws TwitterException {

		twitterPause(userListLimitReset, userListLimitRemaining);
		PagableResponseList<UserList> userLists = _twitter
				.getUserListsOwnerships(DEFAULT_USER, 20, -1);
		userListLimitRemaining = limitRemaining;
		userListLimitReset = limitReset;
		return userLists;
	}

	public Boolean isRunning() {
		return running;
	}

	public void populateMap() {
		try {
			for (UserList list : getUserLists()) {
				for (User user : getUserListMembers(list)) {
					MyUser myUser = null;
					if (myMap.containsKey(user.getScreenName())) {
						myUser = myMap.get(user.getScreenName());
					} else {
						myUser = new MyUser(user);
						myMap.put(user.getScreenName(), myUser);
					}
					myUser.addList(list.getSlug());
				}
			}
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
}
