package java;

public class Obj {

    // https://stackoverflow.com/questions/31019157/how-do-all-classes-inherit-from-object
    // https://docs.oracle.com/javase/7/docs/api/java/lang/package-tree.html

    // https://docs.oracle.com/javase/8/docs/technotes/guides/lang/index.html

    // https://betterexplained.com/articles/how-to-optimize-your-site-with-gzip-compression/

    private static native void registerNatives();

    static {
	registerNatives();
    }

    // public final native Class<?> getClass();

    public native int hashCode();

    public boolean equals(Obj obj) {
	return (this == obj);
    }

    protected native Obj clone() throws CloneNotSupportedException;

    public String toString() {
	return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    // public final native void notify();

    // public final native void notifyAll();

    // public final native void wait(long timeout) throws InterruptedException;

    // public final void wait(long timeout, int nanos) throws
    // InterruptedException {
    // if (timeout < 0) {
    // throw new IllegalArgumentException("timeout value is negative");
    // }
    //
    // if (nanos < 0 || nanos > 999999) {
    // throw new IllegalArgumentException("nanosecond timeout value out of
    // range");
    // }
    //
    // if (nanos > 0) {
    // timeout++;
    // }
    //
    // wait(timeout);
    // }

    // public final void wait() throws InterruptedException {
    // wait(0);
    // }

    protected void finalize() throws Throwable {
    }
}
