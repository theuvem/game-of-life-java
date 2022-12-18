package ConwayGameOfLife;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.Timer;

public class LifeApplication extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static LifeModel model = new LifeModel(); // willekeurig gegenereerd
//	private static LifeModel model = new LifeModel(Patroon.CRASH); // geschreven patroon
//	private static LifeConsoleView view = new LifeConsoleView(model); // console view instantie
	private static LifePanelView view = new LifePanelView(model); // panel view instantie
	private static ControlPanelView viewControls = new ControlPanelView(); // panel view controls instantie
	private static LifeController controller = new LifeController(model);
	private static Timer timer = new Timer(100, controller);
	
	public static void main(String[] args) {
		new LifeApplication(); // maak instantie voor de GUI
		
		try {
			run();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public LifeApplication() {
		this.setBounds(802, 402, 316, 276); // positioneer in midden van scherm met 3:2 resolutie
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // beëindig programma als frame wordt gesloten
		this.setTitle("Conway's Game of Life");
		this.add(view, BorderLayout.NORTH); // voeg panel view instantie toe aan app
		this.add(viewControls, BorderLayout.SOUTH); // voeg control panel instantie toe aan app
		this.setVisible(true);
	}
	
	private static void run() throws InterruptedException {
		timer.start(); // start timer
		
		Thread.sleep(1000); // voeg vertraging toe zodat event kan plaatsvinden voordat programma sluit
	}
	
	public static void toggleTimer() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}

}
