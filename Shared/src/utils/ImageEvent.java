package utils;

import Catalano.Imaging.FastBitmap;

import java.util.EventObject;

/**
 * Created by KYUSS on 30.11.2015.
 *
 */
public class ImageEvent extends EventObject{

    private FastBitmap _image;
    private String _imagePath;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ImageEvent(Object source, FastBitmap image) {
        super(source);
        _image = image;
    }

    public FastBitmap getImage() {
        return _image;
    }

    public void setImage(FastBitmap image) {
        _image = image;
    }

    public String getImagePath() {
        return _imagePath;
    }

    public void setImagePath(String imagePath) {
        _imagePath = imagePath;
    }
}
