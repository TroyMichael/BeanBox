package bean;

import dataContainers.ListenerHandler;
import utils.ImageEvent;
import utils.ImageListener;
import utils.iListenerHandler;

import java.awt.event.ActionEvent;
import java.io.Serializable;

/**
 * Created by KYUSS on 19.11.2015.

 */
public class Source implements ImageListener, Serializable, iListenerHandler {

    private String _imagePath = "loetstellen.jpg";
    private ListenerHandler _listeners = new ListenerHandler();

    public Source(){
    }

    public String getImagePath() {
        return _imagePath;
    }

    public void setImagePath(String imagePath) {
        _imagePath = imagePath;
    }

    public void start(ActionEvent e) {
        ImageEvent event = new ImageEvent(this);
        onImage(event);
    }

    @Override
    public void onImage(ImageEvent e) {
        e.setImagePath(_imagePath);
        _listeners.notifyAllListener(e);
    }

    @Override
    public void addListener(ImageListener listener) {
        _listeners.addListener(listener);
    }

    @Override
    public void removeListener(ImageListener listener) {
        _listeners.removeListener(listener);
    }
}
