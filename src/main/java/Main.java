import javax.swing.JApplet;
import javax.swing.JFrame;

public class Main extends JApplet{

public static void main(String[] args){
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("ShipPong");
        window.setContentPane(new MoveShip(window));
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);     
        
    }
	public Main() {
       this.setContentPane(new MoveShip(null));
    }

}
