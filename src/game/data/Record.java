package game.data;

public class Record implements Comparable<Record>{
    public String user;
    public long time;

    public Record(String user, long time) {
        this.user = user;
        this.time = time;
    }

    @Override
    public int compareTo(Record u) {
        if(u.time > this.time){
            return -1;
        }
        return 1;
    }
}