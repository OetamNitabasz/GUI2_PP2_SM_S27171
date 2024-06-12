package SimpleDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Canvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private Point pointer;
    private Pen pen;
    private final ArrayList<Figure> figures = new ArrayList<>();
    private DrawingMode mode;
    private Color color = Color.GREEN;
    private boolean pressedD = false;
    private boolean modified = false;
    private File file = null;
    private final StatusListener statusListener;

     Canvas(StatusListener statusListener) {
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        this.statusListener = statusListener;
        statusListener.setState(Status.New);
    }

    public Color getPickedColor() {
        return color;
    }

    public void setPickedColor(Color color) {
        if(color != null) {
            this.color = color;
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(pressedD){
           /* Figure clicked = null;
            for(int i = figures.size() - 1; i >= 0 ; i--) {
               var f = figures.get(i);
               if(f.contains(e.getPoint())) {
                   clicked = f;
                   break;
               }
            }
           if(clicked != null) {
               pressedD = false;       // confirmationDialog przejmuje Focus przez co canvas nie dostnie informacji o puszczonym klawiszu, musimy recznie przelaczyc pressedD
               var result = JOptionPane.showConfirmDialog(this, "Delete figure?", "Warning", JOptionPane.YES_NO_OPTION);
               if(result == JOptionPane.YES_OPTION) {
                    figures.remove(clicked);
                    repaint();
               }
           }*/
            List<Figure> deleted = figures.stream().filter(s -> s.contains(e.getPoint())).toList();
            if(!deleted.isEmpty()) {
                pressedD = false;       // confirmationDialog przejmuje Focus przez co canvas nie dostnie informacji o puszczonym klawiszu, musimy recznie przelaczyc pressedD
                var result = JOptionPane.showConfirmDialog(this, "Delete figure?", "Warning", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    removeFigures(deleted);
                }
            }
        } else if (mode == DrawingMode.Pen) {
            var modifier = e.getModifiersEx();
            if ((modifier & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK) {
                pointer = e.getPoint();
                drawPen();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pen = null;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        pointer = null;
    }

    private void drawCircle() {
        if(pointer == null)
            return;
        figures.add(new Circle(pointer));
        repaint();
        setModified();
    }

    private void drawPen() {
        pen = new Pen(pointer, color);
        figures.add(pen);
        setModified();
    }

    private void drawSquare() {
        if(pointer == null)
            return;
        figures.add(new Square(pointer));
        repaint();
        setModified();
    }

    public void clear() {
        figures.clear();
        setModified();
        repaint();
    }

    private void removeFigures( List<Figure> deleted) {
        deleted.forEach(figures::remove);
        repaint();
        setModified();
    }

    public void setDrawingMode(DrawingMode mode) {
        this.mode = mode;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        figures.forEach(f -> f.draw(g));
    }



    @Override
    public void mouseDragged(MouseEvent e) {
        if(pen != null) {
            pen.update(e.getPoint());
            repaint();
        }
        pointer = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       pointer = e.getPoint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F1) {
            if(mode == DrawingMode.Square) {
                drawSquare();
            } else if(mode == DrawingMode.Circle) {
                drawCircle();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            pressedD = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D) {
            pressedD = false;
        }
    }

    public void save() {
        saveAs(file);
    }

    public boolean isModified() {
        return modified;
    }

    private void setModified() {
        modified = true;
        if(!isNew())
            statusListener.setState(Status.Modified);
    }

    private void setSave(File file) {
        this.file = file;
        modified = false;
        statusListener.setState(Status.Saved);
    }

    public void saveAs(File selectedFile) {
        var dst = selectedFile.toPath();
        var data = figures.stream().map(Object::toString).toList();
        try {
            Files.write(dst, data, StandardOpenOption.CREATE);
            setSave(selectedFile);
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Cannot save file. ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void load(File selectedFile) {
        try {
            Scanner scr = new Scanner(selectedFile);
            figures.clear();
            while (scr.hasNextLine()) {
                var line = scr.nextLine();
                if (line.startsWith("Circle,")) {
                    figures.add(new Circle(line));
                } else if (line.startsWith("Square,")) {
                    figures.add(new Square(line));
                } else if(line.startsWith("Pen,")) {
                    figures.add(new Pen(line));
                }
            }
            setSave(selectedFile);
            repaint();
        }catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Cannot load file. " , "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isNew() {
        return file == null;
    }
}
