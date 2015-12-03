package bean;

import Catalano.Imaging.Filters.Opening;
import dataContainers.Image;
import utils.ImageEvent;
import utils.ImageListener;

import javax.swing.*;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class OpeningFilter implements ImageListener{

    private Image _image;

    public OpeningFilter(){}

    public void process() {
        //remove cords
        Opening openingFilter = new Opening(5);
        openingFilter.applyInPlace(_image.getImage());
        JOptionPane.showMessageDialog(null, _image.getImage().toIcon(), "Cropped, removed black, removed noise, removed cords Result", JOptionPane.PLAIN_MESSAGE);
    }


    @Override
    public void onImage(ImageEvent e) {
        process();
    }
}
