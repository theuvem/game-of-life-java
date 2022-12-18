package ConwayGameOfLife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelController implements ActionListener {

	private LifeModel model;
	private CelPositie celPos;
	
	public CelController(LifeModel model, CelPositie celPos) {
		this.model = model;
		this.celPos = celPos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.model.toggle(celPos.rij, celPos.kolom);
	}
	
}
