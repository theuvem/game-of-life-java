package ConwayGameOfLife;

import java.util.Observable;
import java.util.Observer;

public class LifeConsoleView implements Observer {

	private LifeModel model;
	
	public LifeConsoleView(LifeModel model) {
		this.model = model; // instantieer model
		this.model.addObserver(this); // registreer zelf als observer bij model
		
		this.toon();
	}
	
	public void toon() {
		System.out.println(); // lege tekstregel aan bovenkant
		
		for (boolean[] rij : this.model.getGrid()) { // itereer door alle rijen
			System.out.print("  "); // links inspringen per rij
			
			for (boolean cel : rij) { // itereer door alle cellen (kolommen) per rij
				System.out.print(cel ? "X" : "."); // print toestand van cel
			}
			
			System.out.println(); // ga op volgende regel verder na einde van rij
		}
		
		System.out.println(); // lege tekstregel aan onderkant
	}

	@Override
	public void update(Observable model, Object info) {
		this.toon(); // toon grid opnieuw
	}
	
}
