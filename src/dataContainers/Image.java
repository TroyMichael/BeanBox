package dataContainers;

import Catalano.Core.IntRange;
import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Median;
import Catalano.Imaging.Filters.Opening;
import Catalano.Imaging.Filters.ReplaceColor;
import Catalano.Imaging.Tools.Blob;
import Catalano.Imaging.Tools.BlobDetection;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 09.11.2015.
 *
 */
public class Image  {

    private FastBitmap _image;
    private int _rectangleX = 0;
    private int _rectangleY = 50;
    private int _maxX = 448;
    private int _maxY = 50;
    private List <Point> _tolerancePoints;
    private List <Blob> _blobList = new ArrayList<>();

    public Image (String imageURL) {
        _image = new FastBitmap(imageURL);
        fillTolerancePoints();
    }

    public FastBitmap getImage (){
        return _image;
    }

    public void setImage (FastBitmap image){
        _image = image;
    }

    public int getRectangleXValue (){
        return _rectangleX;
    }

    public int getRectangleYValue (){
        return _rectangleY;
    }

    public int getMaxXValue (){
        return _maxX;
    }

    public int getMaxYValue (){
        return _maxY;
    }

    public void setBlobList (List <Blob> blobList){
        _blobList = new ArrayList<>(blobList);
    }

    public List <Blob> getBlobList (){
        return _blobList;
    }

    public void displayImage() {
        //show original image
        JOptionPane.showMessageDialog(null, _image.toIcon(), "Result", JOptionPane.PLAIN_MESSAGE);

        //convert to bufferedImage to crop image
        BufferedImage bf = _image.toBufferedImage();
        bf = bf.getSubimage(_rectangleX, _rectangleY, 448, 50);

        //convert back to fastBitmap and show cropped image
        _image = new FastBitmap(bf);
        JOptionPane.showMessageDialog(null, _image.toIcon(), "Cropped Result", JOptionPane.PLAIN_MESSAGE);

        //set colorRange of which including color should be replaced and define a ReplaceColor object
        IntRange colorRange = new IntRange(0, 37);
        ReplaceColor replaceColorFilter = new ReplaceColor(colorRange, colorRange, colorRange);

        //apply replaceColorFilter and show result
        replaceColorFilter.ApplyInPlace(_image, 255, 255, 255);
        JOptionPane.showMessageDialog(null, _image.toIcon(), "Cropped removed Black Result", JOptionPane.PLAIN_MESSAGE);

        //clean image from noise
        Median medianFilter = new Median(3);
        medianFilter.applyInPlace(_image);
        JOptionPane.showMessageDialog(null, _image.toIcon(), "Cropped, removed black, removed noise Result", JOptionPane.PLAIN_MESSAGE);

        //remove cords
        Opening openingFilter = new Opening(5);
        openingFilter.applyInPlace(_image);
        JOptionPane.showMessageDialog(null, _image.toIcon(), "Cropped, removed black, removed noise, removed cords Result", JOptionPane.PLAIN_MESSAGE);

        //save image
        _image.saveAsPNG("out/CroppedImage.png");

        //detect blobs
        _image.toGrayscale();
        BlobDetection blobDetection = new BlobDetection();
        List <Blob> blobList = blobDetection.ProcessImage(_image);
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

        fillTolerancePoints();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("out/Centroids-Positions", "UTF-8");

            writer.println("Positions of centroids");

            List <Blob> passedBlobs = new ArrayList<>();
            List <Blob> failedBlobs = new ArrayList<>();

            for (Blob b: blobList) {
                if (checkTolerance(b)){
                    passedBlobs.add(b);
                } else {
                    failedBlobs.add(b);
                }
            }

            writer.println("Passed!");
            for (Blob b : passedBlobs){
                writer.println("x: " + (b.getCenter().y + _rectangleX) + " y: " + (b.getCenter().x + _rectangleY)); //fggit catalano doesnt know x-, y-axis
            }
            writer.println("\n Failed:");
            for (Blob b : failedBlobs){
                writer.println("x: " + (b.getCenter().y + _rectangleX) + " y: " + (b.getCenter().x + _rectangleY)); //fggit catalano doesnt know x-, y-axis
            }

            writer.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void fillTolerancePoints() {
        _tolerancePoints = new LinkedList<>();

        _tolerancePoints.add(new Point(6, 72, 5));
        _tolerancePoints.add(new Point(72, 76, 5));
        _tolerancePoints.add(new Point(136, 80, 5));
        _tolerancePoints.add(new Point(201, 79, 5));
        _tolerancePoints.add(new Point(265, 79, 5));
        _tolerancePoints.add(new Point(330, 81, 5));
        _tolerancePoints.add(new Point(395, 80, 5));
    }

    public boolean checkTolerance (Blob blob){
        for (Point point : _tolerancePoints){
            if (point.checkIfTolerated(blob.getCenter().y + _rectangleX, blob.getCenter().x + _rectangleY)){
                return true;
            }
        }
        return false;
    }
}