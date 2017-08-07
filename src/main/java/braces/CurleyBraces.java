package braces;

public class CurleyBraces {

    /**
     * @param args
     */

    String changeme = "hi";

    static {
	greetings = "goodbye world";
	System.out.println("hello");
    }
    static {
	System.out.println("these must be in order");
    }
    {
	System.out.println("yoyoyo");
	changeme = "bye";
	System.out.println(greetings);
    }

    public CurleyBraces() {
	System.out.println(changeme);
	System.out.println(greetings);
    }

    public static void main(String[] args) {
	new CurleyBraces();
	String s = "jdsalkd";
	byte[] b = s.getBytes();

	System.out.println(s.length());
	System.out.println(b.length);
    }

    static String greetings = "Hello world";

    static {
	System.out.println("these must be in order 2");
    }
}
