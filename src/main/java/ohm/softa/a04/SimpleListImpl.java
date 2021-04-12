package ohm.softa.a04;

import java.util.Iterator;

public class SimpleListImpl<T> implements SimpleList<T> {

	// Listenanfang und -größe deklarieren
	private ListElement<T> head;
	private int size;

	// Konstruktor setzt Start auf null
	public SimpleListImpl() {
		head = null;
	}

	// Fügt Objekt am Ende der Liste hinzu
	// Item ist das Item das hinzugefügt wird
	public void add(T item){
		// leere Liste
		if(head == null){
			head = new ListElement<T>(item);
		}

		// Liste mit mind. einem Element
		else {
			// Liste durchlaufen
			ListElement<T> current = head;
			while (current.getNext() != null){
				current = current.getNext();
			}
			// Als Nachfolger neues Listenelement setzen
			current.setNext(new ListElement<T>(item));
		}
		size++;
	}

	// Gibt Listenlänge zurück
	public int size() {
		return size;
	}

	// Neue SimpleList-Instanz mit allen Items der Liste, die Filterkriterien erfüllen
	// filter ist Instanz, die geprüft wird
	// result ist Liste die zurückgegeben wird
	// Wird überschrieben
/*	public SimpleList filter(SimpleFilter filter){
		// neue Ergebnisliste wird erstellt
		SimpleList result = new SimpleListImpl();

		// für jedes Objekt mache folgendes...
		for(Object o : this){
			// Check ob vorhanden
			if(filter.include(o)){
				// wenn ja, zur Liste hinzufügen
				result.add(o);
			}
		}
		return result;
	}*/

	// Wurde vererbt
	// macht "foreachbar", zuerst Iterable, dann Iterator
	@Override
	public Iterator<T> iterator() {
		return new SimpleIterator();
	}

	// Hilfsklasse die Iterator-Interface implementiert
	// non-static, da es sonst nicht das Element am Listenanfang erreichen würde
	private class SimpleIterator implements Iterator<T> {
		// Zugriff auf Listenanfang
		private ListElement<T> current = head;

		// inheritDoc
		// true, wenn nächsten Nachbarn
		@Override
		public boolean hasNext() {
			return current != null;
		}

		// inheritDoc
		// Zugriff aufs nächste Element
		@Override
		public T next() {
			// wieso wird nicht gleich tmp zurückgegeben?
			T tmp = current.getItem();
			current = current.getNext();
			return tmp;
		}
	}

	// Hilfsklasse für Liste
	// static, weil ListElement nicht auf SimpleList-Instanzen zugreifen muss
	private static class ListElement<T>{
		// Es gibt ein Item ...
		private T item;
		// ... mit einer Referenz auf das nächste Element
		private ListElement<T> next;

		// Konstruktor
		ListElement(T item) {
			this.item = item;
			this.next = null;
		}

		// Rückgabe des Elements
		public T getItem() {
			return item;
		}

		// gibt Nachfolger des Listenelements zurück, evtl. null
		public ListElement<T> getNext() { return next; }

		// Verändern des Nachfolger des Listenelements
		public void setNext(ListElement<T> next) {
			this.next = next;
		}
	}

}

