import java.awt.Toolkit;
import java.util.*;

class BeepTask extends TimerTask {
    @Override
    public void run() {
        Toolkit.getDefaultToolkit().beep();
        System.out.println("➡️ Beep la fiecare 2 secunde");
    }
}

class ReminderTask extends TimerTask {
    private String message;

    public ReminderTask(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println("⏰ " + message);
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
            System.out.println("⌛ Timp ramas: " + secondsLeft + " secunde");
            secondsLeft--;
        } else {
            System.out.println("✅ Countdown terminat!");
            this.cancel(); // oprim timerul
        }
    }
}

public class MultiTimerApp {
    public static void main(String[] args) {
        Timer t1 = new Timer(); // Se repetă la fiecare 2 secunde
        Timer t2 = new Timer(); // Programare la ora exactă
        Timer t3 = new Timer(); // Începe după 3 secunde și se repetă

        System.out.println("▶️ Pornim aplicatia...");

        // 1️⃣ Timer care reacționează periodic (la fiecare 2 secunde)
        t1.scheduleAtFixedRate(new BeepTask(), 0, 2000);

        // 2️⃣ Timer care reacționează la o oră exactă (ex: în următorul minut)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10); // rulează peste 10s de la pornire
        Date specificTime = calendar.getTime();
        t2.schedule(new ReminderTask("A trecut ora setată (10 secunde de la start)!"), specificTime);

        // 3️⃣ Timer care începe după 3 secunde și rulează periodic la fiecare 1 secundă
        t3.schedule(new CountdownTask(5), 3000, 1000);
    }
}

