import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

import interfaces.Template;
public class App {

    static Template template;

    public static void main(String[] args) throws Exception {

        FlatLightLaf.setup();

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                template = new Template(1);
            }
        });
        
    }
}
