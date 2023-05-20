import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.SwingWorker;

import com.formdev.flatlaf.FlatLightLaf;

import tasks.WebSocket;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame{

    JPanel panel = (JPanel) this.getContentPane();
    JTextField field = new JTextField();
    JButton button = new JButton("Envoyer");

    Socket socket = new Socket();
    ServerSocket ss;
    OutputStream out;
    PrintWriter writer;
    boolean linked = false;
    

    public Server(){
        
        this.setTitle("Serveur");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(200,100);

        this.setLayout(new BorderLayout());
        panel.add(middle(),BorderLayout.CENTER);
        
        try {
            try_connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                writer = new PrintWriter(out,true);
                writer.println(field.getText());
            }
            
        });
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    WebSocket.erreur = true;
                    socket.close();
                    ss.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Etat de l'erreur à la fermeture du serveur: " + WebSocket.erreur);
            }
        });
        this.setVisible(true);
    }
  
    private JPanel middle(){
        JPanel panel = new JPanel();
        button.setEnabled(false);
        panel.add(field);
        panel.add(button);
        return panel;
    }

    private void try_connect() throws IOException{

        ss = new ServerSocket(8080);
        SwingWorker work = new SwingWorker() {
            @Override
            protected Void doInBackground() throws Exception {

                // ? Attend que le client soit connecté
                
                socket = ss.accept();
                out = socket.getOutputStream();

                return null;
            }

            @Override
            protected void done() {
                button.setEnabled(true);
                linked = true;

            }
        };
        work.execute();

    }

    public static void main(String args[]) {
        FlatLightLaf.setup();
		Server server = new Server();
	}


}
