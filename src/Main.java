import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;

public class    Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Spirala");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        int delta = Integer.parseInt(args[0]);
        int length = Integer.parseInt(args[1]);
        window.getContentPane().add(new Spiral(delta, length));
        Insets insets = window.getInsets();
        window.setSize(insets.left + insets.right + length+3, insets.top + insets.bottom + length+3);
    }
}
class Spiral extends JPanel {
    Graphics2D g;
    int step, length;
    public Spiral(int delta, int length) {
        if(delta < 0 || length < 0) {
            throw new IllegalArgumentException();
        }
        this.step = delta;
        this.length = length;
    }

    public void paint(Graphics gr) {
        this.g = (Graphics2D) gr;
        g.setStroke(new BasicStroke(2));
        printSpiral(1, 1, length, 0, step);
    }

    public void printSpiral(int x, int y, int deltaX, int deltaY, int step) {
        if (Math.abs(deltaX) < step && Math.abs(deltaY) < step) return;
        int newX = x+deltaX;
        int newY = y+deltaY;
        g.drawLine(x, y, newX, newY);
        if(deltaX != 0) {
                deltaY = deltaX;
                deltaX = 0;
        }
        else {
            if(deltaY > 0) deltaX = (deltaY-step)*-1;
            else deltaX = (deltaY+step)*-1;
            deltaY = 0;
        }
        printSpiral(newX, newY, deltaX, deltaY, step);
    }
}