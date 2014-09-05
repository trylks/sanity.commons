package sanity.commons.functional.another;

public class Triple<T1, T2, T3> {

	private T1 subject;
	private T2 predicate;
	private T3 object;

	public Triple(T1 subject, T2 predicate, T3 object) {
		this.setSubject(subject);
		this.setPredicate(predicate);
		this.setObject(object);
	}

	public T1 getSubject () {
		return this.subject;
	}

	public void setSubject (T1 subject) {
		this.subject = subject;
	}

	public T2 getPredicate () {
		return this.predicate;
	}

	public void setPredicate (T2 predicate) {
		this.predicate = predicate;
	}

	public T3 getObject () {
		return this.object;
	}

	public void setObject (T3 object) {
		this.object = object;
	}

}
