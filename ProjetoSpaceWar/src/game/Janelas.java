package game;
import javax.swing.JFrame;

public class Janelas extends JFrame {
	
	public Janelas() {
	    
		add(new Fase());
		setTitle("Space War");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600,600);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setVisible(true);
	       
	}
       
}
