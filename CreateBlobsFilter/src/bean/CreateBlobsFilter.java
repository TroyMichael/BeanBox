package bean;

import Catalano.Imaging.Tools.Blob;
import Catalano.Imaging.Tools.BlobDetection;
import dataContainers.Image;
import filter.AbstractFilter;
import interfaces.*;
import interfaces.Readable;
import utils.ImageEvent;
import utils.ImageListener;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.List;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class CreateBlobsFilter extends AbstractFilter <Image, Image> implements ImageListener{

    private Image _image;

    public CreateBlobsFilter() {

    }

    public CreateBlobsFilter(Readable<Image> input) throws InvalidParameterException {
        super(input);
    }

    public CreateBlobsFilter(Writeable<Image> output) throws InvalidParameterException {
        super(output);
    }

    public CreateBlobsFilter(Readable<Image> input, Writeable<Image> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public void process() {
        //detect blobs
        _image.getImage().toGrayscale();
        BlobDetection blobDetection = new BlobDetection();
        List<Blob> blobList = blobDetection.ProcessImage(_image.getImage());
        blobList.sort(new Comparator<Blob>() {
            @Override
            public int compare(Blob o1, Blob o2) {
                if (o1.getCenter().y > o2.getCenter().y) {
                    return 1;
                } else if (o1.getCenter().y == o2.getCenter().y) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        _image.setBlobList(blobList);
        System.out.println("BlobList size = " + blobList.size());
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
        writeOutput(image);
    }

    @Override
    public void onImage(ImageEvent e) {

    }
}
