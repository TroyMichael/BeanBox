package bean;

import dataContainers.Image;
import filter.AbstractFilter;
import interfaces.*;
import interfaces.Readable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class SaveScreenshotFilter extends AbstractFilter <Image, Image> {

    private Image _image;

    public SaveScreenshotFilter(Readable<Image> input) throws InvalidParameterException {
        super(input);
    }

    public SaveScreenshotFilter(Writeable<Image> output) throws InvalidParameterException {
        super(output);
    }

    public SaveScreenshotFilter(Readable<Image> input, Writeable<Image> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public void process() {
        //save image
        _image.getImage().saveAsPNG("CroppedImage.png");
    }

    @Override
    public Image read() throws StreamCorruptedException {
        _image = readInput();
        process();
        return _image;
    }

    @Override
    public void run() {
    }

    @Override
    public void write(Image image) throws StreamCorruptedException {
        _image = image;
        process();
        writeOutput(_image);
    }
}
