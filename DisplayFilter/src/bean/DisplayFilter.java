package bean;

import dataContainers.Image;
import utils.ImageEvent;
import utils.ImageListener;

import javax.swing.*;

/**
 * Created by Michael on 30.11.2015.
 *
 */
public class DisplayFilter implements ImageListener{

    private Image _image;

    public DisplayFilter(){

    }

    @Override
    public void onImage(ImageEvent e) {
        _image = e.getImage();
        JOptionPane.showMessageDialog(null, _image.getImage().toIcon(), "Result", JOptionPane.PLAIN_MESSAGE);
    }
}
