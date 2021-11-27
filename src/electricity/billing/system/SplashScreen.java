package electricity.billing.system;

import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author souma (SMIT)
 * @version v1.0
 *
 */
public class SplashScreen {

    public void start() {
        SplashFrame splashFrame = new SplashFrame();
        splashFrame.setVisible(true);
        displaySplashAnimation(splashFrame);
    }

    private static void displaySplashAnimation(SplashFrame frame) {
        int i;
        int x = 1;
        for(i=2; i<=600; i+=4,x+=1){
            frame.setLocation(800 - ((i+x)/2),500 - (i/2));
            frame.setSize(i+x,i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

}

class SplashFrame extends JFrame implements Runnable {

    Thread thread;

    public SplashFrame() {
        super("Electricity Billing System");
        setLayout(new FlowLayout());
        addBackgroundImage(); 

        thread = new Thread(this);
        thread.start();
    }

    private void addBackgroundImage() {
        ImageIcon splashIcon = new ImageIcon(SplashFrame.class.getResource("icon/splash_img.jpg"));
        Image splashImage = splashIcon.getImage().getScaledInstance(730, 550, Image.SCALE_SMOOTH);
        splashIcon = new ImageIcon(splashImage);

        JLabel imageLabel = new JLabel(splashIcon);
        this.add(imageLabel);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(7000);
            this.dispose();

            Login loginFrame = new Login();
            loginFrame.setVisible(true);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
