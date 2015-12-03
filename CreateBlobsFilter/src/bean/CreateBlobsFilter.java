package bean;

import Catalano.Imaging.Tools.Blob;
import Catalano.Imaging.Tools.BlobDetection;
import dataContainers.Image;
import utils.ImageEvent;
import utils.ImageListener;

import java.util.Comparator;
import java.util.List;

/**
 * Created by a1aharsim on 17.11.2015.
 *
 */
public class CreateBlobsFilter implements ImageListener{

    private Image _image;

    public CreateBlobsFilter() {

    }

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
    public void onImage(ImageEvent e) {
        process();
    }
}
