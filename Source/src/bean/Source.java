package bean;

import dataContainers.ListenerHandler;
import utils.ImageEvent;
import utils.ImageListener;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by KYUSS on 19.11.2015.

 */
public class Source implements ImageListener, Serializable {

    private String _imagePath = "";
    private ListenerHandler _listeners;

    public Source(){
        _listeners = new ListenerHandler();
    }

    public String getImagePath() {
        return _imagePath;
    }

    public void setImagePath(String imagePath) {
        _imagePath = imagePath;
    }

    public void addEventListener (LoadImageFilter eventListener) {
        _listeners.addListener(eventListener);
    }

    public void removeEventListener (LoadImageFilter eventListener){
        _listeners.removeListener(eventListener);
    }

    public void start(ActionEvent e) {
        System.out.println("hallo action");
        ImageEvent event = new ImageEvent(this);
        onImage(event);
    }

    @Override
    public void onImage(ImageEvent e) {
        e.setImagePath(_imagePath);
        _listeners.notifyAllListener(e);
    }
}
