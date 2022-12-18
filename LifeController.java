package ConwayGameOfLife;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LifeController implements ActionListener {

	private LifeModel model;
	
	public LifeController(LifeModel model) {
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.model.volgendeGeneratie();
	}
	
}
