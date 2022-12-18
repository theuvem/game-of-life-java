package ConwayGameOfLife;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanelView extends JPanel {

	private static final long serialVersionUID = 1L;

	public ControlPanelView() {
		JButton pauseBtn = new JButton("Pause");
		
		pauseBtn.addActionListener(new TimerController(this));
		
		this.add(pauseBtn, 0);
	}
	
	public void toggleBtnTxt() {
		if (((JButton) this.getComponent(0)).getText() == "Pause") {
			((JButton) this.getComponent(0)).setText("Resume");
		} else {
			((JButton) this.getComponent(0)).setText("Pause");
		}
		
		this.revalidate(); // revalideer view
		this.repaint(); // ververs view
	}
	
}
