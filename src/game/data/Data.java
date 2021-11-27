package game.data;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Data {
    private final API api;

    public Data() {
        api = new API();
    }

    public List<User> getAllRecords() throws Exception {
        String data = api.sendGET();

        List <User> users = parseJSONtoUsers(data);

        return orderUsersDescending(users);
    }

    public List<User> getOwnRecords(String user) throws Exception {
        String data = api.sendGET();

        List <User> users = parseJSONtoUsers(data);
        users = users.stream().filter(u -> user.equals(u.user)).toList();

        return orderUsersDescending(users);
    }

    public void postRecord(String name, long seconds) throws IOException {
        User user = new User(name, seconds);
        String parsedUser = parseUserToJSON(user);

        api.sendPOST(parsedUser);
    }

    private List<User> parseJSONtoUsers(String json){
        return Arrays.stream(json.split("},")).map(x -> {
            x = x.replaceAll("[{}\"]", "").replaceAll(",", ":");
            List<String> fields = Arrays.stream(x.split(":")).toList();
            return new User(fields.get(4), Integer.parseInt(fields.get(2)));
        }).toList();
    }

    private String parseUserToJSON(User user){
        return String.format("{\"time\": \"%d\", \"user\": \"%s\"}", user.time, user.user);
    }

    private List<User> orderUsersDescending(List<User> users){
        User[] usersArr = new User[users.size()];
        usersArr = users.toArray(usersArr);
        Arrays.sort(usersArr);

        return Arrays.stream(usersArr).toList();
    }

}