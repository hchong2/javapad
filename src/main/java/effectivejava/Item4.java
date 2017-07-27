package effectivejava;

public class Item4 {

    /**
     * Enforce noninstantiability with a private constructor.
     * 
     * Non-instantiable classes: Math, Collections, Arrays
     * 
     * @param args
     */

    // Noninstantiable utility class
    public static class UtilityClass {
	// Suppress default constructor for noninstantiability
	private UtilityClass() {
	    throw new AssertionError();
	}
    }

    public static void main(String[] args) {
    }

}