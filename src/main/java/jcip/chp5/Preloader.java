package jcip.chp5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {

    public static class ProductInfo {

    }

    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
	public ProductInfo call() throws DataLoadException {
	    return loadProductInfo();
	}
    });

    private final Thread thread = new Thread(future);

    public void start() {
	thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
	try {
	    return future.get();
	} catch (ExecutionException e) {
	    Throwable cause = e.getCause();
	    if (cause instanceof DataLoadException)
		throw (DataLoadException) cause;
	    else
		throw launderThrowable(cause);
	}
    }

}
