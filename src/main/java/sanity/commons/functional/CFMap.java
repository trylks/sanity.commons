package sanity.commons.functional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import sanity.commons.concurrency.Globals;

public class CFMap<I, O> implements FMap<I, O> {

	private CurrifiedCallableFunction<I, O> f;

	public CFMap(Function<I, O> f) {
		this.f = new CurrifiedCallableFunction<I, O>(f);
	}

	public Iterable<O> apply (Iterable<I> input) {
		return new CFMapIterable(input);
	}

	public class CFMapIterable implements Iterable<O> {

		private Iterable<I> input;

		public CFMapIterable(Iterable<I> input) {
			this.input = input;
		}

		public Iterator<O> iterator () {
			return new CFMapIterator(this.input.iterator());
		}

	}

	public class CFMapIterator implements Iterator<O> {

		private Iterator<I> input;
		private LinkedList<Future<O>> queue = new LinkedList<Future<O>>();
		private O nextElement = null;

		public CFMapIterator(Iterator<I> input) {
			this.input = input;
			for (int i = Runtime.getRuntime().availableProcessors(); i > 0; i--)
				this.enqueueIfPossible();
		}

		void enqueueIfPossible () {
			if (this.input.hasNext())
				this.queue.add(Globals.pool.submit(CFMap.this.f.callable(this.input.next())));
		}

		public boolean hasNext () {
			if (this.nextElement != null)
				return true;
			this.updateNextIfPossible();
			return this.nextElement != null;
		}

		private O updateNextIfPossible () {
			O oldnext = this.nextElement;
			this.nextElement = null;
			while (this.nextElement == null && this.queue.size() > 0) {
				this.enqueueIfPossible();
				try {
					this.nextElement = this.queue.poll().get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			return oldnext;
		}

		public O next () {
			this.enqueueIfPossible();
			return this.updateNextIfPossible();
		}

		public void remove () {
			throw new UnsupportedOperationException();
		}

	}

}
