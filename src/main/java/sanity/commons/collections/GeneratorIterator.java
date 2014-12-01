package sanity.commons.collections;

import java.util.Iterator;

import sanity.commons.functional.Condition;
import sanity.commons.functional.Function;

public class GeneratorIterator<T> implements Iterator<T> {

	private T current;
	private Function<T, T> function;
	private Condition<T> condition;

	public GeneratorIterator(Function<T, T> function, Condition<T> condition, T start) {
		this.current = start;
		this.function = function;
		this.condition = condition;
	}

	public boolean hasNext () {
		return this.condition.check(this.current);
	}

	public T next () {
		T res = this.current;
		this.current = this.function.apply(this.current);
		return res;
	}

	public void remove () {
		// TODO Auto-generated method stub
	}

}
