package utils;

import dataContainers.ListenerHandler;

/**
 * Created by Michael on 03.12.2015.
 *
 */
public interface iListenerHandler {

    void addListener(ImageListener listener);
    void removeListener(ImageListener listener);

}
