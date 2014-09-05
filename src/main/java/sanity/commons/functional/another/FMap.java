package sanity.commons.functional.another;

import java.util.Iterator;

public class FMap<In, Out> implements Iterable<Out> {

	private Iterable<In> l;
	private Function<In, Out> f;

	public Iterator<Out> iterator () {
		return new FMapIterator<In, Out>(this.f, this.l.iterator());
	}

	public FMap(Function<In, Out> f, Iterable<In> l) {
		this.f = f;
		this.l = l;
	}

}
