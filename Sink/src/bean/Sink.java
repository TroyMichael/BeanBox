package bean;


import dataContainers.Image;
import filter.AbstractFilter;
import interfaces.Readable;

import java.io.StreamCorruptedException;

/**
 * Created by Michael on 22.11.2015.
 *
 */

public class Sink extends AbstractFilter<Image, Object> {

    public Sink(Readable<Image> input) {
        super(input);
    }

    public Sink() {}
    @Override
    public void process() {

    }

    @Override
    public Object read() throws StreamCorruptedException {
        readInput();
        System.out.println("Finished");
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(Image value) throws StreamCorruptedException {

    }
}