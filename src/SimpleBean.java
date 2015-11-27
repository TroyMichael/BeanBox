import java.awt.*;
import java.io.Serializable;

/**
 * Created by KYUSS on 26.11.2015.
 */
public class SimpleBean extends Canvas implements Serializable {

    private Color _color = Color.green;

    public SimpleBean (){
        setSize(60, 40);
        setBackground(Color.red);
    }

    public Color getColor (){
        return _color;
    }

    public void setColor (Color color){
        _color = color;
        repaint();
    }

    public void paint (Graphics graphics){
        graphics.setColor(_color);
        graphics.fillRect(20, 5, 20, 30);
    }
}
