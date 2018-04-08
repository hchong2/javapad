package jcip.chp2;

import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {
    private long count = 0;

    public long getCount() {
	return count;
    }

    public void service(ServletRequest req, ServletResponse resp) {
	BigInteger i = extractFromRequest(req);
	BigInteger[] factors = factor(i);
	encodeIntoResponse(resp, factors);
    }
}
