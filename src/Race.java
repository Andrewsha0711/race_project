import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Race extends JComponent implements ActionListener{

    private static final int DEF_WIDTH = 1280;
    private static final int DEF_HEIGHT = 720;
	
	ArrayList<Racer> racers;
	ArrayList<RacerThread> racersThreads;
	ArrayList<Racer> winners;
	Timer raceTimer = new Timer(20, this);
	
	Image img;
	int racersCount = 0;
	
    public Dimension getPreferredSize() {
        return new Dimension(DEF_WIDTH, DEF_HEIGHT);
    }
	
	public Race(int count) throws Exception{	
		
		BufferedImage buffimage = ImageIO.read(new File("res\\road.jpg"));
		this.img = buffimage.getScaledInstance(DEF_WIDTH, DEF_HEIGHT, Image.SCALE_DEFAULT);
		this.racersCount = count;
		this.racers = new ArrayList<Racer>();
		this.racersThreads = new ArrayList<RacerThread>();
		this.winners = new ArrayList<Racer>();
		for(int i = 0; i<this.racersCount; i++) {
			Racer racer = new Racer(0,DEF_HEIGHT/2 + (DEF_HEIGHT/(2.5*this.racersCount))*i,3, DEF_WIDTH, "racer" + i);
			this.racers.add(racer);
		}
		this.raceTimer.start();
	}
	public void addRacer() {
		this.racersCount += 1;
	}
	
	public void paint(Graphics g) {
		//this.setSize(new Dimension(1280, 720));
		g = (Graphics2D) g;
		g.drawImage(img, 0, 0, null);
		for(int i = 0; i<this.racersCount; i++) {
			g.drawImage(this.racers.get(i).img, (int)this.racers.get(i).x, (int)this.racers.get(i).y, null);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(this.winners.size() == this.racers.size()) {
			this.raceTimer.stop();
			
			Image imge = new ImageIcon("res/car" + this.winners.get(0).id.charAt(this.winners.get(0).id.length()-1) + ".png").getImage();
			this.getGraphics().drawImage(imge, 0,0, null);
			System.out.println("Гонка завершена");
			this.winners.clear();
		}
			
		if(this.raceTimer.isRunning()) {
			repaint();
		}
	}
	
	public void setStartPosition() {
		for (int i = 0; i < this.racers.size(); i++) {
			this.racers.get(i).x = 0;
		}
	}
	
	public void startRace() {
		if(this.racersThreads.size() != 0 || this.winners.size() != 0) {
			for(int i = 0; i<racersThreads.size(); i++) {
				if(this.racersThreads.get(i).isAlive())
					this.racersThreads.get(i).interrupt();
			}
			this.racersThreads.clear();
			this.setStartPosition();
			this.winners.clear();
		}
		for(int i = 0; i<this.racersCount; i++) {
			this.racersThreads.add(new RacerThread(this.racers.get(i),this.winners));
			this.racersThreads.get(i).start();
		}
		if(!this.raceTimer.isRunning())
			this.raceTimer.start();
	}
}
