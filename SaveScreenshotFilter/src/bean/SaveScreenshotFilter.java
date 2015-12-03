package bean;

import dataContainers.Image;
import utils.ImageEvent;
import utils.ImageListener;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class SaveScreenshotFilter implements ImageListener {

    private Image _image;

    public SaveScreenshotFilter(){}

    public void process() {
        //save image
        _image.getImage().saveAsPNG("CroppedImage.png");
    }

    @Override
    public void onImage(ImageEvent e) {
        process();
    }
}
