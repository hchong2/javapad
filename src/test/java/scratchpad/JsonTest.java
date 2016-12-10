package scratchpad;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JsonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws JSONException {
		
		final JSONObject o = new JSONObject();
	      o.put("name", "+5V");
	      o.put("current_voltage", 3.36);
	      o.put("maximum_voltage", 3.632);
	      o.put("minimum_voltage", 2.96);
	      o.put("healthy", true);

	      final String s = (String) o.get("name");
	      System.out.println(s);
	      o.get("findme");
	}

}
