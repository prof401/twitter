package net.april1.twitter;

import twitter4j.RateLimitStatus;
import twitter4j.RateLimitStatusEvent;
import twitter4j.RateLimitStatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public abstract class TwitterTransaction {
	protected Twitter _twitter;
	protected int limitReset = 0;
	protected int limitRemaining = 0;
	protected int nextReset = 0;

	public TwitterTransaction() {
		_twitter = (new TwitterFactory()).getInstance();
		_twitter.addRateLimitStatusListener(new RateLimitStatusListener() {
			@Override
			public void onRateLimitReached(RateLimitStatusEvent event) {
				System.err.println(System.currentTimeMillis());
				System.err.println("Reached Limit["
						+ event.getRateLimitStatus().getLimit()
						+ "], Remaining["
						+ event.getRateLimitStatus().getRemaining() + "]");
				System.err.println("Reached Reset Time["
						+ event.getRateLimitStatus().getResetTimeInSeconds()
						+ "], Sec to Reset["
						+ event.getRateLimitStatus().getSecondsUntilReset()
						+ "]");
			}

			@Override
			public void onRateLimitStatus(RateLimitStatusEvent event) {
				RateLimitStatus r = event.getRateLimitStatus();
				limitReset = r.getSecondsUntilReset();
				limitRemaining = r.getRemaining();
				nextReset = r.getResetTimeInSeconds() + 1;
			}
		});
	}

	protected void twitterPause(int limitReset, int limitRemaining) {
//		System.err.println(limitReset + " / " + limitRemaining);
		int waitTime = Math.max(limitReset, 10);
		if (limitRemaining > 1)
			waitTime = (limitReset / (limitRemaining - 1));
//		System.err.print(waitTime);
		try {
			Thread.sleep(waitTime * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.err.println(" *");
	}
}
