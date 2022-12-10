import java.util.ArrayList;

public class RaceThread extends Thread {
    Race race;

    RaceThread(Race value) {
        this.race = value;
    }

    public void run() {

        if (!Thread.interrupted()) {
            for (int i = 0; i < this.race.racersThreads.size(); i++) {
                if (this.race.racersThreads.get(i).isAlive()) {
                    this.race.racersThreads.get(i).interrupt();
                }
            }
        }
        this.race.startRace();
    }
}
