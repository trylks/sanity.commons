package java.utilities.functional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.utilities.concurrency.Globals;

public class CFMap<I, O> implements FMap<I, O> {

	private CurrifiedCallableFunction<I, O> f;

	private CFMap(Function<I, O> f) {
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
		private LinkedList<Future<O>> queue;

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
			return this.queue.size() > 0;
		}

		public O next () {
			this.enqueueIfPossible();
			try {
				return this.queue.poll().get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public void remove () {
			throw new UnsupportedOperationException();
		}

	}

}
