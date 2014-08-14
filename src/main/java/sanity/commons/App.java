package sanity.commons;

import java.util.Arrays;

import sanity.commons.functional.CFMap;
import sanity.commons.functional.Function;
import sanity.commons.statics.StringOps;

/**
 * Hello world!
 * 
 */
public class App {

	static class DelayFunction implements Function<Character, String> {

		public String apply (Character input) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return input.toString();
		}

	}

	public static void main (String[] args) {
		long start = System.currentTimeMillis();
		CFMap<Character, String> map = new CFMap<Character, String>(new DelayFunction());
		Iterable<String> message = map.apply(Arrays.asList('H', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd', '!'));
		System.out.println(StringOps.mkstring(message, "", "", ""));
		System.out.println(System.currentTimeMillis() - start);
	}
}
