package finance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Finance {

    public static class Record {
	String type;
	String transactionDate;
	String postDate;
	String description;
	Double amount;

	public Record(String type, String transDate, String postDate, String desc, Double amount) {
	    this.type = type;
	    this.transactionDate = transDate;
	    this.postDate = postDate;
	    this.description = desc;
	    this.amount = amount;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

	public String getTransactionDate() {
	    return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
	    this.transactionDate = transactionDate;
	}

	public String getPostDate() {
	    return postDate;
	}

	public void setPostDate(String postDate) {
	    this.postDate = postDate;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public Double getAmount() {
	    return amount;
	}

	public void setAmount(Double amount) {
	    this.amount = amount;
	}
    }

    public static Double getCreditSum(List<Record> recs) {
	Double sum = 0.0;

	for (Record r : recs) {
	    int comp = r.getAmount().compareTo(new Double(0));
	    if (comp == -1)
		sum += r.getAmount();
	}
	return sum;
    }

    public static Double getDebitSum(List<Record> recs) {
	Double sum = 0.0;

	for (Record r : recs) {
	    int comp = r.getAmount().compareTo(0.0);
	    if (comp == 1)
		sum += r.getAmount();
	}
	return sum;
    }

    public static void main(String[] args) throws Exception {
	String path = "/Users/mistercharles/Downloads/";
	String filename = "Chase7252_Activity_20170307.CSV";

	List<String> lines = FileUtils.readLines(new File(path + filename), "utf-8");
	lines = lines.subList(1, lines.size());

	List<Record> records = new ArrayList<Record>();
	for (String l : lines) {
	    String[] s = l.split(",");
	    records.add(new Record(s[0], s[1], s[2], s[3], Double.parseDouble(s[4])));
	}

	System.out.println(getDebitSum(records));
	System.out.println(getCreditSum(records));
    }
}
