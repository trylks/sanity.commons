package java.utilities.functional;

import java.util.concurrent.Callable;

public class CallableFunction<I, O> implements Function<I, O>, Callable<O> {
	private I input;
	private Function<I, O> f;

	public CallableFunction(Function<I, O> function, I input) {
		this.input = input;
		this.f = function;
	}

	public O call () throws Exception {
		return this.f.apply(this.input);
	}

	public O apply (I input) {
		return this.f.apply(input);
	}

}
