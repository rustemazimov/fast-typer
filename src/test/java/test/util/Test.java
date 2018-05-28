package test.util;

import java.util.Timer;
import java.util.TimerTask;

public class Test {
	private static int milliseconds = 0;
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println(milliseconds++);
			}
		};
		timer.schedule(task, 0, 1);
	}
}
