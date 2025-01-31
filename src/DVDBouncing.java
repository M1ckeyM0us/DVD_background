import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class DVDBouncing extends JPanel implements ActionListener {
    private int x = 100, y = 100, dx = 2, dy = 2;
    private final int DVD_WIDTH = 200, DVD_HEIGHT = 140;
    private Timer timer;
    private Image logo;
    private Color backgroundColor;

    public DVDBouncing() {
        setPreferredSize(new Dimension(800, 600));
        timer = new Timer(10, this);
        timer.start();

        // Load the logo image from resources folder
        URL logoURL = getClass().getResource("/resources/dvd_logo.png"); // Adjust the path if needed
        if (logoURL != null) {
            logo = new ImageIcon(logoURL).getImage();
        } else {
            System.out.println("Image not found!");
        }

        // Initialize the background color
        backgroundColor = Color.BLACK;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the background color
        setBackground(backgroundColor);

        // Draw the DVD logo image
        g.drawImage(logo, x, y, DVD_WIDTH, DVD_HEIGHT, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the position of the DVD logo
        x += dx;
        y += dy;

        // Bounce off the edges of the screen and change background color
        if (x <= 0 || x >= getWidth() - DVD_WIDTH) {
            dx = -dx;
            changeBackgroundColor(); // Change color when bouncing off the X-axis
        }
        if (y <= 0 || y >= getHeight() - DVD_HEIGHT) {
            dy = -dy;
            changeBackgroundColor(); // Change color when bouncing off the Y-axis
        }

        repaint(); // Redraw the screen
    }

    // Method to change the background color randomly
    private void changeBackgroundColor() {
        // Generate random RGB values
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);

        // Set the new background color
        backgroundColor = new Color(r, g, b);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DVD Bouncing");
        DVDBouncing dvdBouncing = new DVDBouncing();
        frame.add(dvdBouncing);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
