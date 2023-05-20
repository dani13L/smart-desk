package services;

import java.util.List;

import javax.swing.SwingWorker;

import tasks.WebSocket;
import utilities.ToggleButton;

public class WebSocketService {

    // Créer un WebSocket
    WebSocket web_socket = new WebSocket();
    private ToggleButton toggle;

    // ? Constructeur
    public WebSocketService(ToggleButton toggla) {

        // Bouton switch
        this.toggle = toggla;

        SwingWorker fetchBadge = new SwingWorker<Void, Boolean>() {

            @Override
            protected Void doInBackground() throws Exception {
                int count = 0;
                for (;;) {

                    // ? Pause avant chaque boucle afin de laisser respirer le ram :)
                    Thread.sleep(100);

                    // ? Essayer de connecter avec le module
                    if (WebSocket.erreur) {
                        web_socket.connecter();
                        if (count > 0) {
                        } else {
                            publish(WebSocket.erreur);
                            count++;
                        }
                    } else {
                        web_socket.getID_badge();
                        if (count < 1) {
                        } else {
                            publish(WebSocket.erreur);
                            count--;
                        }
                    }

                    // ? Essayer de récupérer l'identité d'un badge

                }
            }

            @Override
            protected void process(List<Boolean> chunks) {

                boolean state = chunks.get(chunks.size() - 1);
                toggle.setSelected(!state);

            }

        };
        fetchBadge.execute();

    }

    // Getters and Setters
    public WebSocket getWeb_socket() {
        return web_socket;
    }

    public void setWeb_socket(WebSocket web_socket) {
        this.web_socket = web_socket;
    }

}
