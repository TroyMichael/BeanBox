package filter.implementedFilters;

import Catalano.Core.IntRange;
import Catalano.Imaging.Filters.ReplaceColor;
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
public class SegmentationFilter extends AbstractFilter <Image, Image>{

    private Image _image;

    public SegmentationFilter(Readable<Image> input) throws InvalidParameterException {
        super(input);
    }

    public SegmentationFilter(Writeable<Image> output) throws InvalidParameterException {
        super(output);
    }

    public SegmentationFilter(Readable<Image> input, Writeable<Image> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public void process() {
        //set colorRange of which including color should be replaced and define a ReplaceColor object
        IntRange colorRange = new IntRange(0, 37);
        ReplaceColor replaceColorFilter = new ReplaceColor(colorRange, colorRange, colorRange);

        //apply replaceColorFilter and show result
        replaceColorFilter.ApplyInPlace(_image.getImage(), 255, 255, 255);
        JOptionPane.showMessageDialog(null, _image.getImage().toIcon(), "Cropped removed Black Result", JOptionPane.PLAIN_MESSAGE);
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
