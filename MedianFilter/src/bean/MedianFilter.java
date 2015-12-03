package bean;

import Catalano.Imaging.Filters.Median;
import dataContainers.Image;
import utils.ImageEvent;
import utils.ImageListener;

import javax.swing.*;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class MedianFilter implements ImageListener{

    private Image _image;

    public MedianFilter(){}

    public void process() {
        //clean image from noise
        Median medianFilter = new Median(3);
        medianFilter.applyInPlace(_image.getImage());
        JOptionPane.showMessageDialog(null, _image.getImage().toIcon(), "Cropped, removed black, removed noise Result", JOptionPane.PLAIN_MESSAGE);
    }


    @Override
    public void onImage(ImageEvent e) {
        process();
    }
}
