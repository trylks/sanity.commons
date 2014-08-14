package java.utilities.functional;

public class CurrifiedCallableFunction<In, Out> implements Function<In, Out> {

	private Function<In, Out> f;

	public CurrifiedCallableFunction(Function<In, Out> f) {
		this.f = f;
	}

	public Out apply (In input) {
		return this.f.apply(input);
	}

	public CallableFunction<In, Out> callable (In input) {
		return new CallableFunction<In, Out>(this.f, input);
	}

}
