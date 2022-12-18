package ConwayGameOfLife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerController implements ActionListener {

	private ControlPanelView view;
	
	public TimerController(ControlPanelView cpv) {
		this.view = cpv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LifeApplication.toggleTimer();
		this.view.toggleBtnTxt();
	}

}
