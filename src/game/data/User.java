package game.data;

public class User implements Comparable<User>{
    public String user;
    public long time;

    public User(String user, long time) {
        this.user = user;
        this.time = time;
    }

    @Override
    public int compareTo(User u) {
        if(u.time > this.time){
            return -1;
        }
        return 1;
    }
}