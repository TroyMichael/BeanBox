package bean;

import Catalano.Imaging.FastBitmap;
import dataContainers.Image;
import dataContainers.ListenerHandler;
import utils.ImageEvent;
import utils.ImageListener;
import utils.iListenerHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Michael on 30.11.2015.
 *
 */
public class DisplayFilter extends Panel implements ImageListener, iListenerHandler {

    private ListenerHandler _listeners = new ListenerHandler();

    public DisplayFilter(){

    }

    @Override
    public void onImage(ImageEvent e) {

        FastBitmap fbimage = e.getImage().getImage();
        if (fbimage != null) {
            JPanel panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    BufferedImage image = fbimage.toBufferedImage();
                    Graphics2D graphics2d = (Graphics2D) g;
                    graphics2d.drawImage(image, 0, 0, null);
                    super.paintComponents(g);
                }
            };
            panel.setSize(fbimage.getWidth(), fbimage.getHeight());
            this.removeAll();
            add(panel);
            panel.repaint();
            this.repaint();
        } else {
            System.out.println("Image == null");
        }
    }

    @Override
    public void addListener(ImageListener listener) {
        _listeners.addListener(listener);
    }

    @Override
    public void removeListener(ImageListener listener) {
        _listeners.removeListener(listener);
    }
}
