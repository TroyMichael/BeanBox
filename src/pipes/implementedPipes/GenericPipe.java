package pipes.implementedPipes;

import filter.AbstractFilter;
import interfaces.IOable;

import java.io.StreamCorruptedException;

/**
 * Created by KYUSS on 19.11.2015.
 *
 */
public class GenericPipe <T> implements IOable<T, T> {

    private AbstractFilter _filter;

    public GenericPipe (AbstractFilter filter){
        _filter = filter;
    }

    public GenericPipe (){

    }

    public T read (){
        try {
            return (T) _filter.read();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFilter (AbstractFilter filter){
        _filter = filter;
    }

    @Override
    public void write(T object) throws StreamCorruptedException {
        _filter.write(object);
    }
}