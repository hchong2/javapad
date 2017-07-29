package finance;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class QuandlDataset {
    String id;
    String datasetCode;
    String databaseCode;
    String name;
    String description;
    String refreshedAt;
    String newestAvailableDate;
    String oldestAvailableDate;
    String frequency;
    String type;
    String startDate;
    String endDate;
    List<Pair<String, Double>> data;

}
