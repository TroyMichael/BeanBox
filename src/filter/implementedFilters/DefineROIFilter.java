package filter.implementedFilters;

import Catalano.Imaging.FastBitmap;
import dataContainers.Image;
import filter.AbstractFilter;
import interfaces.*;
import interfaces.Readable;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class DefineROIFilter extends AbstractFilter <Image, Image> {
    private Image _image;

    public DefineROIFilter(Readable<Image> input) throws InvalidParameterException {
        super(input);
    }

    public DefineROIFilter(Writeable<Image> output) throws InvalidParameterException {
        super(output);
    }

    public DefineROIFilter(Readable<Image> input, Writeable<Image> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public void process() {
        //convert to bufferedImage to crop image
        BufferedImage bufferedImage = _image.getImage().toBufferedImage();
        bufferedImage = bufferedImage.getSubimage(_image.getRectangleXValue(), _image.getRectangleYValue(), _image.getMaxXValue(), _image.getMaxYValue());

        //convert back to fastBitmap and show cropped image
        _image.setImage(new FastBitmap(bufferedImage));
        JOptionPane.showMessageDialog(null, _image.getImage().toIcon(), "Cropped Result", JOptionPane.PLAIN_MESSAGE);
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
