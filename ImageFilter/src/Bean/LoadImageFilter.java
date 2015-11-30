package Bean;

import dataContainers.Image;
import filter.AbstractFilter;
import interfaces.Writeable;
import interfaces.Readable;

import javax.swing.*;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by Michael on 09.11.2015.
 *
 */
public class LoadImageFilter extends AbstractFilter <String, Image> {
    private String _imagePath;
    private Image _image;

    public LoadImageFilter() {

    }

    public LoadImageFilter(Readable<String> input) throws InvalidParameterException {
        super(input);
    }

    public LoadImageFilter(Writeable<Image> output) throws InvalidParameterException {
        super(output);
    }

    public LoadImageFilter(Readable<String> input, Writeable<Image> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public Image read() throws StreamCorruptedException {
        _imagePath = readInput();
        process();
        return _image;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(String imagePath) throws StreamCorruptedException {
        _imagePath = imagePath;
        process();
        writeOutput(_image);
    }

    @Override
    public void process() {
        _image = new Image(_imagePath);
        JOptionPane.showMessageDialog(null, _image.getImage().toIcon(), "Result", JOptionPane.PLAIN_MESSAGE);
    }

    public String getImagePath() {
        return _imagePath;
    }

    public void setImagePath(String imagePath) {
        _imagePath = imagePath;
    }

    public Image getImage() {
        return _image;
    }

    public void setImage(Image image) {
        _image = image;
    }
}
