package game.objects;
public class Timer {

    private long startTimer = 0;
    private long stopTimer = 0;
    private boolean stopWatchRunning = false;


    public void startTimer() {
        this.startTimer = System.nanoTime();
        this.stopWatchRunning = true;
    }


    public void stopTimer() {
        this.stopTimer = System.nanoTime();
        this.stopWatchRunning = false;
    }


    public long getElapsedSeconds() {
        long elapsedTime;

        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - startTimer);
        else
            elapsedTime = (stopTimer - startTimer);

        return elapsedTime / 1000000000;
    }


}