package dataContainers;

import utils.ImageEvent;
import utils.ImageListener;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 03.12.2015.
 *
 */
public class ListenerHandler {

    private List<ImageListener> _listeners;

    public ListenerHandler() {
        _listeners = new LinkedList<>();
    }

    public void addListener(ImageListener listener) {
        _listeners.add(listener);
    }

    public void removeListener(ImageListener listener) {
        _listeners.remove(listener);
    }

    public void notifyAllListener(ImageEvent e) {
        _listeners.forEach(listener -> listener.onImage(e));
    }
}
