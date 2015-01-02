package net.april1.twitter;

import java.util.concurrent.ConcurrentMap;

public class GatherAll {

	public static void main(String[] args) {
		ConcurrentMap<String, MyUser> myMap = new java.util.concurrent.ConcurrentHashMap<String, MyUser>(
				1000);

		Friends friend = new Friends(myMap);
		ListFriends list = new ListFriends(myMap);
		list.run();
		friend.run();
		synchronized (friend.isRunning()) {
		}
		System.out.println(MyUser.headerString());
		for (String screenName : new java.util.TreeSet<String>(myMap.keySet())) {
			MyUser user = myMap.get(screenName);
			System.out.println(user);
		}
	}
}
