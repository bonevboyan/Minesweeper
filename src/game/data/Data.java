package game.data;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Data {
    private final API api;
    private final String[] endpoints;
    private List<Record> records;

    public Data() {
        api = new API();
        endpoints = new String[]{
                "easy.json",
                "medium.json",
                "expert.json"
        };
    }

    public List<Record> getAllRecords(int difficulty) throws Exception {
        String data = api.sendGET(endpoints[difficulty]);

        List <Record> records = parseJSONtoRecord(data);

        return orderRecordsDescending(records);
    }

    public List<Record> getOwnRecords(String user, int difficulty) throws Exception {
        String data = api.sendGET(endpoints[difficulty]);

        List <Record> records = parseJSONtoRecord(data);
        records = records.stream().filter(u -> user.equals(u.user)).toList();

        return orderRecordsDescending(records);
    }

    public void postRecord(String name, long seconds, int difficulty) throws IOException {
        Record record = new Record(name, seconds);
        String parsedUser = parseRecordToJSON(record);

        api.sendPOST(endpoints[difficulty], parsedUser);
    }

    private List<Record> parseJSONtoRecord(String json){
        return Arrays.stream(json.split("},")).map(x -> {
            x = x.replaceAll("[{}\"]", "").replaceAll(",", ":");
            List<String> fields = Arrays.stream(x.split(":")).toList();
            return new Record(fields.get(4), Integer.parseInt(fields.get(2)));
        }).toList();
    }

    private String parseRecordToJSON(Record record){
        return String.format("{\"time\": \"%d\", \"user\": \"%s\"}", record.time, record.user);
    }

    private List<Record> orderRecordsDescending(List<Record> records){
        Record[] usersArr = new Record[records.size()];
        usersArr = records.toArray(usersArr);
        Arrays.sort(usersArr);

        return Arrays.stream(usersArr).toList();
    }

}