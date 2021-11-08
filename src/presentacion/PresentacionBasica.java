package presentacion;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.tabladatos.TablaDatosPanel;

public class PresentacionBasica {
	
	protected JFrame ventanaPrincipal;
	
	public void iniciarAplicacion() {
		JFrame j = new JFrame("Administracion de Consorcios");
		
		JPanel windowContainer = new JPanel();
		windowContainer.setLayout(new BoxLayout(windowContainer, BoxLayout.Y_AXIS));

		JPanel bottomSection = new JPanel();
		bottomSection.setLayout(new BoxLayout(bottomSection, BoxLayout.X_AXIS));
		
		
		ActionPanel actionPanel = new ActionPanel();
		TablaDatosPanel tabla = new TablaDatosPanel();
		Navbar navbar = new Navbar(actionPanel, tabla);
		
		windowContainer.add(navbar);
		
		bottomSection.add(actionPanel);
		bottomSection.add(tabla);
		
		windowContainer.add(bottomSection);
		j.setSize(750, 500);
		j.getContentPane().add(windowContainer);
		
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}

}
