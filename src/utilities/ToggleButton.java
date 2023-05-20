package utilities;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.MouseInputAdapter;

public class ToggleButton extends Component {

    // Attribut

    private float location;
    private boolean selected;
    private boolean mouseOver;
    private float speed = 1.2f;

    private List<ToggleSelectedEvent> events;

    // ? Constructeur
    public ToggleButton() {

        setBackground(Colors.green);
        setPreferredSize(new Dimension(32, 19));
        events = new ArrayList<>();

        location = 2;
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver = false;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (mouseOver) {
                        selected = !selected;
                        animate();
                        fire();
                    }
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {

        // Créer un graphic 2D
        Graphics2D g2d = (Graphics2D) g;

        // Mettre de l'antialias sur le graphic
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        float alpha = getAlfa();

        if (alpha < 1) {
            g2d.setColor(Colors.stroke);
            g2d.fillRoundRect(0, 0, width, height, 20, 20);
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width, height, 20, 20);
        g2d.setColor(Colors.backgrounds);
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.fillOval((int) location, 2, height - 4, height - 4);

        super.paint(g);
    }

    private float getAlfa() {

        float width = getWidth() - getHeight();
        float alpha = (location - 2) / width;

        if (alpha < 0) {
            alpha = 0;
        }
        if (alpha > 1) {
            alpha = 1;
        }

        return alpha;
    }

    // Pour basculer le toggle
    public void animate(){
        
        SwingWorker animationWorker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {

                if (selected) {

                    int endLocation = getWidth() - getHeight() + 2;

                    while (location < endLocation) {
                        location += speed;
                        publish();
                        Thread.sleep(7);
                    }

                    location = endLocation;
                    publish();

                } else {

                    int endLocation = 2;

                    while (location > endLocation) {
                        location -= speed;
                        publish();
                        Thread.sleep(7);
                    }

                    location = endLocation;
                    publish();

                }
                return null;
            }

            @Override
            protected void process(List<Void> chunks) {
                repaint();
            }

            @Override
            protected void done() {
            }

        };
        animationWorker.execute();
    }

    // Pour ajouter un listener
    public void addToggleListener(ToggleSelectedEvent event){
        events.add(event);
    }

    // Lancer chaque evenement dans la liste 
    public void fire(){
        for(ToggleSelectedEvent event:events){

            event.onSelected(selected);

        }
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        animate();
        fire();
    }
}


