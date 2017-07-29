package finance;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class StockApp {

    /**
     * https://www.quandl.com/api/v3/datasets/WIKI/TSLA/data.json?api_key=1Uza5Y
     * -2jNRC4Z52pGdD
     **/
    public static void main(String[] args) throws Exception {
	gold();
    }

    public static void gold() throws Exception {
	String apiKey = "1Uza5Y-2jNRC4Z52pGdD";
	String urlPath = "https://www.quandl.com/api/v3/datasets/BUNDESBANK/BBK01_WT5511.json";
	String fullUrl = String.format("%s?api_key=%s", urlPath, apiKey);

	HttpClient client = HttpClientBuilder.create().build();
	HttpGet request = new HttpGet(fullUrl);

	// request.addHeader("User-Agent", USER_AGENT);
	HttpResponse response = client.execute(request);

	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

	StringBuffer result = new StringBuffer();
	String line = "";
	while ((line = rd.readLine()) != null) {
	    result.append(line);
	}

	rd.close();

	p(result.toString());

	JSONObject json = new JSONObject(result.toString());
	p(json);
	JSONObject dataset = json.getJSONObject("dataset");
	JSONArray data = dataset.getJSONArray("data");

	for (int i = 0; i < data.length(); i++) {
	    JSONArray datum = data.getJSONArray(i);
	    String date = datum.getString(0);
	    double value = datum.getDouble(1);

	    p(date);
	    p(value);
	}
	// p(json.getJSONObject("dataset").toString());
	// p(json.getJSONObject("dataset").get("name"));
    }

    public static void run() throws IOException {
	String fileLocation = "src/main/tsla.json";
	analyze(fileLocation);
	p("#######################");
	fileLocation = "src/main/amzn.json";
	analyze(fileLocation);
    }

    public static void analyze(String file) throws IOException {
	File f = new File(file);
	String content = FileUtils.readFileToString(f, Charset.defaultCharset());
	JSONObject json = new JSONObject(content);

	process(json);
    }

    public static void process(JSONObject json) {
	JSONObject top = json.getJSONObject("dataset_data");
	String startDate = top.getString("start_date");
	String endDate = top.getString("end_date");

	JSONArray data = top.getJSONArray("data");

	int newHighCount = 0;
	double newHighValue = 0.0;

	int greenDays = 0;
	int redDays = 0;

	double biggestGain = 0.0;
	double biggestLoss = 0.0;
	long cumulativeVolume = 0L;
	long greenDayVolume = 0L;
	long redDayVolume = 0L;

	long greens = 0;
	long reds = 0;

	double percentGain = 0.0;
	double percentLoss = 0.0;

	// Reverse iterate data
	Double yesterdayClose = null;
	Double yesterdayDiff = null;
	double afterHourGains = 0.0;
	double afterHourLosses = 0.0;
	double afterHourTotal = 0.0;

	int afterHourGainAfterRedDayCount = 0;
	int afterHourLossAfterRedDayCount = 0;
	int afterHourGainAfterGreenDayCount = 0;
	int afterHourLossAfterGreenDayCount = 0;

	List<Double> prices = new ArrayList<>();
	for (int i = data.length() - 1; i > 0; i--) {

	    // if (i > 365)
	    // continue;

	    JSONArray datum = data.getJSONArray(i);

	    double high = datum.getDouble(2);

	    double open = datum.getDouble(1);
	    double close = datum.getDouble(4);
	    double diff = close - open;

	    int volume = datum.getInt(5);
	    cumulativeVolume += volume;
	    prices.add(close);

	    if (yesterdayClose != null) {
		double afterHourDiff = open - yesterdayClose;
		afterHourTotal += afterHourDiff;
		if (afterHourDiff > 0.0) {
		    afterHourGains += afterHourDiff;
		} else if (afterHourDiff < 0.0) {
		    afterHourLosses += afterHourDiff;
		}

		if (yesterdayDiff != null) {
		    if (diff < 0.0) {
			if (afterHourDiff > 0.0) {
			    afterHourGainAfterRedDayCount++;
			} else if (afterHourDiff < 0.0) {
			    afterHourLossAfterRedDayCount++;
			}
		    } else if (diff > 0.0) {
			if (afterHourDiff > 0.0) {
			    afterHourGainAfterGreenDayCount++;
			} else if (afterHourDiff < 0.0) {
			    afterHourLossAfterGreenDayCount++;
			}
		    }
		}
	    }

	    yesterdayDiff = diff;

	    yesterdayClose = close;
	    double percentage = diff / close;

	    if (diff < 0.0) {
		redDays++;
		redDayVolume += volume;
		reds += diff;
		percentLoss = Math.min(percentLoss, percentage);
	    }
	    if (diff > 0.0) {
		greenDays++;
		greenDayVolume += volume;
		greens += diff;
		percentGain = Math.max(percentGain, percentage);
	    }

	    biggestGain = Math.max(biggestGain, diff);
	    biggestLoss = Math.min(biggestLoss, diff);

	    newHighValue = Math.max(newHighValue, high);
	    if (newHighValue == high)
		newHighCount++;
	}

	p("New high count:" + newHighCount);
	p("Highest value: " + newHighValue);
	p("Green days: " + greenDays);
	p("Red days: " + redDays);
	p("Biggest gain: " + biggestGain);
	p("Biggest loss: " + biggestLoss);
	p("Cumulative volume: " + cumulativeVolume);
	p("Green day volume: " + greenDayVolume);
	p("Red day volume: " + redDayVolume);

	p((redDayVolume - greenDayVolume) / (double) redDayVolume);

	p("Greens: " + greens);
	p("Reds: " + reds);
	p(percentGain);
	p(percentLoss);

	p(prices);
	p("After hour total: " + afterHourTotal);
	p("After hour gains: " + afterHourGains);
	p("After hour losses: " + afterHourLosses);

	p("After hour gain after red day: " + afterHourGainAfterRedDayCount);
	p("After hour loss after red day: " + afterHourLossAfterRedDayCount);

	p("After hour gain after green day: " + afterHourGainAfterGreenDayCount);
	p("After hour loss after green day: " + afterHourLossAfterGreenDayCount);
    }

    /**
     * Stock market hours are 9:30am to 4pm EST. After-hours trading occurs
     * between 4pm to 8pm EST.
     * 
     * Most of gains are realized during after-hours in which institutions have
     * more influence than independent investors. Do these gains occur right
     * after a red day?
     **/
    static <T> void p(T arg) {
	System.out.println(arg);
    }
}
