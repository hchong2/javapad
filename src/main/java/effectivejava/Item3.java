package effectivejava;

public class Item3 {

    /**
     * Enforce the singleton property with a private constructor or an enum type
     */

    // Singleton with public final field
    public static class Elvis {
	public static final Elvis INSTANCE = new Elvis();

	private Elvis() {
	}

	public void leaveTheBuilding() {
	}
    }

    // Singleton with static factory
    public static class Elvis2 {
	private static final Elvis2 INSTANCE = new Elvis2();

	private Elvis2() {

	}

	public static Elvis2 getInstance() {
	    return INSTANCE;
	}

	public void leaveTheBuilding() {
	}
    }

    // Enum singleton - the preferred approach
    public enum Elvis3 {
	INSTANCE;
	public void leaveTheBuilding() {

	}
    }

    public static void main(String[] args) {

    }
}
