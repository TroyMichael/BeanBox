package bean;

import Catalano.Core.IntRange;
import Catalano.Imaging.Filters.ReplaceColor;
import dataContainers.Image;
import utils.ImageEvent;
import utils.ImageListener;

import javax.swing.*;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class SegmentationFilter implements ImageListener{

    private Image _image;

    public SegmentationFilter(){}

    public void process() {
        //set colorRange of which including color should be replaced and define a ReplaceColor object
        IntRange colorRange = new IntRange(0, 37);
        ReplaceColor replaceColorFilter = new ReplaceColor(colorRange, colorRange, colorRange);

        //apply replaceColorFilter and show result
        replaceColorFilter.ApplyInPlace(_image.getImage(), 255, 255, 255);
        JOptionPane.showMessageDialog(null, _image.getImage().toIcon(), "Cropped removed Black Result", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void onImage(ImageEvent e) {
        process();
    }
}
