import java.awt.Toolkit;
import java.util.*;

class BeepTask extends TimerTask {
    @Override
    public void run() {
        Toolkit.getDefaultToolkit().beep();
        System.out.println("🔔 Timer 1: beep la fiecare 2 secunde.");
    }
}

class ReminderTask extends TimerTask {
    private String message;

    public ReminderTask(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println("⏰ Timer 2: " + message);
    }
}

class CountdownTask extends TimerTask {
    private int secondsLeft;

    public CountdownTask(int seconds) {
        this.secondsLeft = seconds;
    }

    @Override
    public void run() {
        if (secondsLeft > 0) {
            System.out.println("⌛ Timer 3: mai sunt " + secondsLeft + " secunde...");
            secondsLeft--;
        } else {
            System.out.println("✅ Timer 3: countdown terminat!");
            this.cancel();
        }
    }
}

public class MultiTimerApp {
    public static void main(String[] args) {

        System.out.println("🚀 Pornim aplicația MultiTimerApp...\n");

        // Timer 1 — rulează la fiecare 2 secunde
        Timer t1 = new Timer();
        System.out.println("🔹 Am pornit Timer 1 (sunet la fiecare 2 secunde)");
        t1.scheduleAtFixedRate(new BeepTask(), 0, 2000);

        // Timer 2 — la 10 secunde după pornire
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        Date specificTime = calendar.getTime();
        Timer t2 = new Timer();
        System.out.println("🔹 Am pornit Timer 2 (mesaj după 10 secunde)");
        t2.schedule(new ReminderTask("A trecut ora setată!"), specificTime);

        // Timer 3 — countdown
        Timer t3 = new Timer();
        System.out.println("🔹 Am pornit Timer 3 (countdown de 5 secunde după 3 secunda)");
        t3.schedule(new CountdownTask(5), 3000, 1000);
    }
}
