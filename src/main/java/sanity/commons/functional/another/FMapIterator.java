package sanity.commons.functional.another;

import java.util.Iterator;

public class FMapIterator<In, Out> implements Iterator<Out> {

	private Function<In, Out> f;
	private Iterator<In> iterator;
	private Out next;

	public FMapIterator(Function<In, Out> f, Iterator<In> iterator) {
		this.f = f;
		this.iterator = iterator;
		if (this.iterator.hasNext())
			this.next = this.f.apply(this.iterator.next());
		else
			this.next = null;
	}

	public boolean hasNext () {
		return this.next != null;
	}

	public Out next () {
		Out res = this.next;
		if (this.iterator.hasNext())
			this.next = this.f.apply(this.iterator.next());
		else
			this.next = null;
		return res;
	}

	public void remove () {
		throw new UnsupportedOperationException();
	}

}
