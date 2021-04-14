package ohm.softa.a04.tests;

import ohm.softa.a04.SimpleFilter;
import ohm.softa.a04.SimpleList;
import ohm.softa.a04.SimpleListImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public class SimpleListTests {

	private final Logger logger = LogManager.getLogger();
	// Integer, nicht int
	private SimpleList<Integer> testList;

	@BeforeEach
	void setup(){
		// Hier darf kein <T> eingesetzt werden, sondern konkreter Datentyp
		testList = new SimpleListImpl<>();

		// Liste wird gefüllt
		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
	}

	@Test
	void testAddElements(){
		logger.info("Testing if adding and iterating elements is implemented correctly");
		int counter = 0;
		for(Object o : testList){
			counter++;
		}
		assertEquals(5, counter);
	}

	@Test
	void testSize(){
		logger.info("Testing if size() method is implemented correctly");
		assertEquals(5, testList.size());
	}

	@Test
	void testFilterAnonymousClass(){
		logger.info("Testing the filter possibilities by filtering for all elements greater 2");
		SimpleList<Integer> result = testList.filter(new SimpleFilter<Integer>() {
			@Override
			public boolean include(Integer item) {
				int current = (int)item;
				return current > 2;
			}
		});

		for(Integer i : result){
			assertTrue(i > 2);
		}
	}

	@Test
	void testFilterLambda(){
		logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
		SimpleList<Integer> result = testList.filter(o -> ((int) o) % 2 == 0);
		for(Integer i : result){
			assertTrue(i % 2 == 0);
		}
	}

	@Test
	void testSimpleListMap(){
		logger.info("Testing default map method");
		// Einsetzen von Funktion (nur jedes zweite Element)
		SimpleList<Integer> mapped = testList.map(i -> i + 1);
		assertEquals(5, mapped.size());
		for(Integer i : mapped) {
			assertTrue(i > 1);
			assertTrue(i < 7);
		}
	}

	@Test
	void testAddDefault(){
		logger.info("Testing AddDefault");
		testList.addDefault(Integer.class);
	}



	@Test
	void testMap() {
		logger.info("Testing default map method by mapping every value to its square");
		// Math.Pow = Basis, Exponent
		SimpleList<Double> result = testList.map(i -> Math.pow(i, 2));
		Iterator<Integer> origIt = testList.iterator();
		Iterator<Double> mapIt = result.iterator();
		while (origIt.hasNext() && mapIt.hasNext()) {
			assertEquals(Math.pow(origIt.next(), 2), mapIt.next(), 0.1);
		}
	}

	@Test
	void testMapRiedhammer() {
		SimpleList<String> sl = testList.map(Object::toString);

		Iterator<Integer> i1 = testList.iterator();
		Iterator<String> i2 = sl.iterator();

		while(i1.hasNext() && i2.hasNext())
		{assertEquals(i1.next().toString(), i2.next());}

		assertFalse(i1.hasNext());
		assertFalse(i2.hasNext());
	}


	@Test
	void testSimpleListMapChangedType() {
		logger.info("Testing default map method by mapping every integer to a char value");
		SimpleList<Character> mapped = testList.map(i -> ((char) (i + 64)));
		assertEquals(5, mapped.size());
	}
}