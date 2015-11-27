package filter.implementedFilters;

import Catalano.Imaging.Tools.Blob;
import dataContainers.Image;
import filter.AbstractFilter;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class CountBallsFilter extends AbstractFilter <Image, Image> {

    private Image _image;

    public CountBallsFilter(Readable<Image> input) throws InvalidParameterException {
        super(input);
    }

    public CountBallsFilter(Writeable<Image> output) throws InvalidParameterException {
        super(output);
    }

    public CountBallsFilter(Readable<Image> input, Writeable<Image> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public void process() {

        try {
            PrintWriter writer = new PrintWriter("Centroids-Positions.txt", "UTF-8");

            writer.println("Number of centroids found: " + _image.getBlobList().size());
            writer.println("Positions of centroids");

            List<Blob> passedBlobs = new ArrayList<>();
            List<Blob> failedBlobs = new ArrayList<>();

            for (Blob blob : _image.getBlobList()) {
                if (_image.checkTolerance(blob)) {
                    passedBlobs.add(blob);
                } else {
                    failedBlobs.add(blob);
                }
            }

            writer.println("Passed:");
            for (Blob b : passedBlobs) {
                writer.println("x: " + (b.getCenter().y + _image.getRectangleXValue()) + " y: " + (b.getCenter().x + _image.getRectangleYValue())); //fggit catalano doesnt know x-, y-axis
            }
            writer.println("\nFailed:");
            for (Blob b : failedBlobs) {
                writer.println("x: " + (b.getCenter().y + _image.getRectangleXValue()) + " y: " + (b.getCenter().x + _image.getRectangleYValue())); //fggit catalano doesnt know x-, y-axis
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
        System.out.println("DONE!");
    }
}
