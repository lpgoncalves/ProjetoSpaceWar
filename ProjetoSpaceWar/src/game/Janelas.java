package game;
import javax.swing.JFrame;

public class Janelas extends JFrame {
	
	public Janelas() {
	    
		add(new Fase());
		
		setTitle("Space War");//Seta o titulo para o frame.
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600,600);//Seta o tamano do frame.
	    setLocationRelativeTo(null);//Resposanvel por deixar centralizado.
	    setResizable(false);//Não é possivel redimensionar a janela.
	    setVisible(true);//Deixa visivel o frame.
	       
	}
       
}
