import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class FacialFilter implements PlugInFilter {
    public int setup(String args, ImagePlus im) {
        return DOES_RGB + DOES_STACKS;
    }


    public void run(ImageProcessor ip) {
        int width = ip.getWidth();     
        int height = ip.getHeight();  
        float[] hsv = new float[3];  
        int min = 30; 
        int max = 90;

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int color = ip.getPixel(col, row);
                Color.RGBtoHSB((color & 0xff0000) >> 16, (color & 0x00ff00) >> 8, color & 0x0000ff, hsv);
                int sv = (int) (hsv[1] * hsv[2] * 255);
                if (sv < min || sv > max) {
                    ip.putPixel(col, row, 0xffffff);
                }
            }
        }
    }
}
