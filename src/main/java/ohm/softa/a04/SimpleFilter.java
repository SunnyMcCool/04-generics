package ohm.softa.a04;

@FunctionalInterface
public interface SimpleFilter <T>{

	// item ist das zu bewertende Objekt
	// gibt true zurück, wenn Objekt vorhanden
	boolean include(T item);
}
