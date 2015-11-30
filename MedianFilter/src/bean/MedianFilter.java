package bean;

import Catalano.Imaging.Filters.Median;
import dataContainers.Image;
import filter.AbstractFilter;
import interfaces.*;
import interfaces.Readable;

import javax.swing.*;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class MedianFilter extends AbstractFilter <Image, Image>{

    private Image _image;

    public MedianFilter(Readable<Image> input) throws InvalidParameterException {
        super(input);
    }

    public MedianFilter(Writeable<Image> output) throws InvalidParameterException {
        super(output);
    }

    public MedianFilter(Readable<Image> input, Writeable<Image> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public void process() {
        //clean image from noise
        Median medianFilter = new Median(3);
        medianFilter.applyInPlace(_image.getImage());
        JOptionPane.showMessageDialog(null, _image.getImage().toIcon(), "Cropped, removed black, removed noise Result", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public Image read() throws StreamCorruptedException {
        _image = readInput();
        process();
        return _image;
    }

    @Override
    public void run() { }

    @Override
    public void write(Image image) throws StreamCorruptedException {
        _image = image;
        process();
        writeOutput(_image);
    }
}
