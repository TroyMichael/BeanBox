package bean;


import utils.ImageEvent;
import utils.ImageListener;

/**
 * Created by Michael on 22.11.2015.
 *
 */

public class Sink implements ImageListener {

    public Sink() {}

    public void process() {
        System.out.println("Finished");
    }

    @Override
    public void onImage(ImageEvent e) {
        process();
    }
}