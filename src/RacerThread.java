import java.util.ArrayList;

public class RacerThread extends Thread{

	private volatile boolean canRun = true;
	Racer racer;
	ArrayList<Racer> winners;
	RacerThread(Racer value, ArrayList<Racer> result){
		this.racer = value;
		this.winners = result;
	}
	
	public void changeAction() {
		canRun = !canRun;
	}
	
	public void run() {
		if(!Thread.interrupted()) {
			while(this.racer.x < this.racer.finish) {
				try {
					this.racer.move();
					sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
					//e.printStackTrace();
				}
			}
			racer.setResult(winners);
		}
	}
}
