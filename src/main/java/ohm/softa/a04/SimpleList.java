package ohm.softa.a04;

// musste hinzugefügt werden
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T>{
	// Fügt Objekt ans Listenende
	void add(T item);

	// gibt Länge zurück
	int size();

	// Hinzufügen einer default-Instanz in die Liste durch default-Konstruktor
	default void addDefault(Class<T> c){
		// neue Instanz erstellen und hinzufügen
		try {
			this.add(c.getDeclaredConstructor().newInstance());

		}
		// Wenn Erstellung nicht möglich
		// Exception werfen
		catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	// T = Typ des Inputs
	// R = Typ des Ergebnisses
	// default <R> SimpleList<R>
	// <R> wird bis jetzt noch nicht erwähnt, daher muss es hier erwähnt werden
	default <R> SimpleList<R> map (Function<T,R> transform)
	{
		// Neue, leere Liste
		SimpleList<R> result;

		// Wenn Erstellung von neuer Instanz möglich
		try {
			result = (SimpleList<R>) getClass().getDeclaredConstructor().newInstance();
		}
		// Wenn Erstellung von neuer Instanz nicht möglich
		catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			result = new SimpleListImpl<R>();
		}

		// Für jedes T wird zu R umgewandelt und hinzugefügt
		for(T t : this){
			result.add(transform.apply(t));
		}
		return result;
	}

	// Generiert neue Liste und verwendet den Filter
	// gibt gefilterte Liste zurück
	default SimpleList<T> filter(SimpleFilter<T> filter){

		// Neue, leere Liste
		SimpleList<T> result;

		// Wenn neue Instanz möglich
		try {
			result = (SimpleList<T>) getClass().getDeclaredConstructor().newInstance();
		}
		// Wenn neue Instanz nicht möglich
		catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			// you can replace the type arguments required to invoke the constructor
			// of a generic class with an empty set of type arguments (<>) as long as
			// the compiler can determine, or infer, the type arguments from the context
			result = new SimpleListImpl<T>();
		}

		// Für jedes T wird Filter getestet und hinzugefügt
		for (T t : this) {
			// Includes()-Methode checkt bei Arrays ob bestimmter Wert enthalten, gibt true/false zurück
			if (filter.include(t)) {
				result.add(t);
			}
		}
		// Neue Liste zurückgeben
		return result;
	}
}
