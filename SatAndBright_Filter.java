import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class SatAndBright_Filter implements PlugInFilter {

    public int setup(String args, ImagePlus im) {
        return DOES_RGB + DOES_STACKS;
    }

    public void run(ImageProcessor ip) {
        int width = ip.getWidth();
        int height = ip.getHeight();
        float[] hsv = new float[3];

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int[] rgb = ip.getPixel(col, row, null);

                Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsv); 
                float product = hsv[1] * hsv[2]; 
                int grayRep = (int) (product * 255); 
                ip.putPixel(col, row, new int[]{grayRep, grayRep, grayRep});
            }
        }
    }
}
