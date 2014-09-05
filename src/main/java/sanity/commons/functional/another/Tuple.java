package sanity.commons.functional.another;

public class Tuple<T1, T2> {
	private T1 first;
	private T2 second;

	public Tuple(T1 first, T2 second) {
		this.setFirst(first);
		this.setSecond(second);
	}

	public T1 getFirst () {
		return this.first;
	}

	public void setFirst (T1 first) {
		this.first = first;
	}

	public T2 getSecond () {
		return this.second;
	}

	public void setSecond (T2 second) {
		this.second = second;
	}
}
