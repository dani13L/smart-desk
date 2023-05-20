package tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class WebSocket {

    // Nouveau Socket
    private Socket socket = null;

    // Port
    static final int PORT = 8080;

    // Signal d'erreur
    public static volatile boolean erreur = true;

    // ID Badge
    public static String badge = "";

    // Streamer
    BufferedReader br = null;

    // Message
    public static String message = "";

    // Constructor
    public WebSocket() {
    }

    // Connecter au serveur
    public void connecter() {

        try {

            WebSocket.erreur = false;
            socket = new Socket("localhost", PORT);

            // Ouvrir un tampon de cannal de communication
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            WebSocket.erreur = true;
        }
    }

    // Fonction qui recupère l'ID du badge
    public void getID_badge() {
        // Verifier si la communication avec le serveur n'est pas interompu

        try {
            if (socket.getInetAddress().isReachable(1000)) {
                // il y un message
                if (br.ready()) {

                    // Lire les données
                    WebSocket.badge = br.readLine();
                    System.out.println(WebSocket.badge);

                }
                

            } else {
                // Signaler une erreur
                WebSocket.erreur = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    // Getter and Setter

}
