package bean;

import filter.AbstractFilter;
import interfaces.Writeable;

import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.io.StreamCorruptedException;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by KYUSS on 19.11.2015.

 */
public class Source extends AbstractFilter <String, String> implements EventListener {

    private String _imagePath ="";

    private List<LoadImageFilter> _listener = new LinkedList<>();

    public Source(){
    }

    public Source (String imagePath) {
        _imagePath = imagePath;
    }

    public Source (Writeable<String> output, String imagePath){
        super(output);
        _imagePath = imagePath;
    }

    public Source (Writeable<String> output){
        super(output);
    }


    @Override
    public String read() throws StreamCorruptedException {
        return _imagePath;
    }

    @Override
    public void process() {

    }

    @Override
    public void run() {
        try {
            write(_imagePath);
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String imagePath) throws StreamCorruptedException {
        writeOutput(imagePath);
    }

    public String getImagePath() {
        return _imagePath;
    }

    public void setImagePath(String imagePath) {
        _imagePath = imagePath;
    }

    public void addEventListener (LoadImageFilter eventListener) {
        _listener.add(eventListener);
    }

    public void removeEventListener (LoadImageFilter eventListener){
        _listener.remove(eventListener);
    }



    public void start(ActionEvent e) {
        _listener.forEach(listener -> listener.process());
    }
}
