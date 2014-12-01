package sanity.commons.collections;

import java.util.Iterator;

import sanity.commons.functional.Condition;
import sanity.commons.functional.Function;

public class Generator<T> implements Iterable<T> {

	private Function<T, T> function;
	private Condition<T> condition;
	private T start;

	public Generator(Function<T, T> function, Condition<T> condition, T start) {
		this.function = function;
		this.condition = condition;
		this.start = start;
	}

	public Iterator<T> iterator () {
		return new GeneratorIterator<T>(this.function, this.condition, this.start);
	}

}
