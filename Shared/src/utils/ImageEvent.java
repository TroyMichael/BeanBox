package utils;

import Catalano.Imaging.FastBitmap;
import dataContainers.Image;

import java.util.EventObject;

/**
 * Created by KYUSS on 30.11.2015.
 *
 */
public class ImageEvent extends EventObject{

    private Image _image;
    private String _imagePath;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ImageEvent(Object source, Image image) {
        super(source);
        _image = image;
    }

    public ImageEvent(Object source) {
        super(source);
    }

    public Image getImage() {
        return _image;
    }

    public void setImage(Image image) {
        _image = image;
    }

    public String getImagePath() {
        return _imagePath;
    }

    public void setImagePath(String imagePath) {
        _imagePath = imagePath;
    }
}
