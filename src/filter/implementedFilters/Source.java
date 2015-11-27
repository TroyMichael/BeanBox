package filter.implementedFilters;

import filter.AbstractFilter;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.StreamCorruptedException;

/**
 * Created by KYUSS on 19.11.2015.

 */
public class Source extends AbstractFilter <String, String>{

    private String _imagePath;

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
}
