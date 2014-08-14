package sanity.commons.statics;

import java.util.Iterator;

public class StringOps {
	public static String mkstring (Iterable<String> parts, String sep, String start, String end) {
		StringBuffer res = new StringBuffer(start);
		Iterator<String> piterator = parts.iterator();
		if (piterator.hasNext())
			res.append(piterator.next());
		while (piterator.hasNext())
			res.append(sep + piterator.next());
		res.append(end);
		return res.toString();
	}
}
