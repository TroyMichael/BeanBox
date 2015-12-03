package bean;

import dataContainers.Image;
import filter.AbstractFilter;
import interfaces.*;
import utils.ImageEvent;
import utils.ImageListener;

import javax.swing.*;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by Michael on 30.11.2015.
 *
 */
public class DisplayFilter extends AbstractFilter<Image, Image> implements ImageListener{

    private Image _image;

    public DisplayFilter(){

    }

    public DisplayFilter(interfaces.Readable<Image> input, Writeable<Image> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public void process() {
        JOptionPane.showMessageDialog(null, _image.getImage().toIcon(), "Result", JOptionPane.PLAIN_MESSAGE);
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
    public void write(Image value) throws StreamCorruptedException {
        _image = value;
        process();
        writeOutput(_image);
    }

    @Override
    public void onImage(ImageEvent e) {

    }
}
