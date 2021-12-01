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

        List <Record> records = parseJSONtoUsers(data);

        return orderUsersDescending(records);
    }

    public List<Record> getOwnRecords(String user, int difficulty) throws Exception {
        String data = api.sendGET(endpoints[difficulty]);

        List <Record> records = parseJSONtoUsers(data);
        records = records.stream().filter(u -> user.equals(u.user)).toList();

        return orderUsersDescending(records);
    }

    public void postRecord(String name, long seconds, int difficulty) throws IOException {
        Record record = new Record(name, seconds);
        String parsedUser = parseUserToJSON(record);

        api.sendPOST(endpoints[difficulty], parsedUser);
    }

    private List<Record> parseJSONtoUsers(String json){
        return Arrays.stream(json.split("},")).map(x -> {
            x = x.replaceAll("[{}\"]", "").replaceAll(",", ":");
            List<String> fields = Arrays.stream(x.split(":")).toList();
            return new Record(fields.get(4), Integer.parseInt(fields.get(2)));
        }).toList();
    }

    private String parseUserToJSON(Record record){
        return String.format("{\"time\": \"%d\", \"user\": \"%s\"}", record.time, record.user);
    }

    private List<Record> orderUsersDescending(List<Record> records){
        Record[] usersArr = new Record[records.size()];
        usersArr = records.toArray(usersArr);
        Arrays.sort(usersArr);

        return Arrays.stream(usersArr).toList();
    }

}