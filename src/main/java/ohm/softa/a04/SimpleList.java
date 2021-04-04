package ohm.softa.a04;

public interface SimpleList <T> extends Iterable {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T item);

	void addDefault(T item);


	/**
	 * @return current size of the list
	 */
	int size();

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	SimpleList<T> filter(SimpleFilter<T> filter);
}
