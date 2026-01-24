package xyz.starrymic.classes;

import xyz.starrymic.config.ConfigHandler;
import xyz.starrymic.tasks.SendMessageTimerTask;

import java.util.Timer;

public class MessageTimer {
    private final Timer _timer = new Timer("message timer");
    SendMessageTimerTask timerTask;

    public MessageTimer() {
        // Initiate the TimerTask
        timerTask = new SendMessageTimerTask();

        // Start the timer
        long intervalMinutes = (long) ConfigHandler.config.get().MessageInterval * 60000; // Convert minutes to ms

        // Waits 30 seconds so the server is actually ready by the time this all goes through.
        _timer.scheduleAtFixedRate(timerTask, 10000, intervalMinutes);
    }
}
