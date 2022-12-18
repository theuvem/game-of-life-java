package ConwayGameOfLife;

import java.awt.Color;
import java.awt.GridLayout;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LifePanelView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	
	private LifeModel model;
	
	public LifePanelView(LifeModel model) {
		this.model = model; // instantieer model
		this.model.addObserver(this); // registreer zelf als observer bij model
		
		this.setLayout(new GridLayout(this.model.getRijen(), this.model.getKolommen()));
		
		this.initGrid();
	}
	
	private void initGrid() {
		for (int i = 0; i < this.model.getGrid().length; i++) {
			for (int j = 0; j < this.model.getGrid()[i].length; j++) {
				JButton btn = new JButton();
				
				if (this.model.getGrid()[i][j]) {
					btn.setBackground(Color.WHITE); // levende cel
				} else {
					btn.setBackground(Color.BLACK); // dode cel
				}
				
				btn.addActionListener(new CelController(this.model, new CelPositie(i, j)));
				
				this.add(btn); // voeg knop toe aan view grid
			}
		}
	}
	
	private void updateCel(CelPositie cel) {
		int celNummer = (cel.rij * this.model.getKolommen()) + cel.kolom; // coördinaten van 2D naar 1D
		
		if (this.model.getGrid()[cel.rij][cel.kolom]) {
			this.getComponent(celNummer).setBackground(Color.WHITE);
		} else {
			this.getComponent(celNummer).setBackground(Color.BLACK);
		}
	}
	
	private void updateGrid() { // update alle cellen in grid
		for (int i = 0; i < this.model.getGrid().length; i++) {
			for (int j = 0; j < this.model.getGrid()[i].length; j++) {
				this.updateCel(new CelPositie(i, j));
			}
		}
	}

	@Override
	public void update(Observable model, Object info) {
		if (info != null) { // controleer of cel positie is meegestuurd
			this.updateCel((CelPositie) info);
		} else {
			this.updateGrid();
		}
		
		this.revalidate(); // revalideer view
		this.repaint(); // ververs view
	}

}
