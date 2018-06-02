package cz.uhk.ppro.inzeraty.util;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ImageDownscaler downscales image when adding new advert if bigger than 250px X 250px
 * Uses Imagescalr library
 */
@Component
public class ImageDownscaler {
    private static final int MAX_WIDTH = 250;
    private static final int MAX_HEIGHT = 250;

    public ImageDownscaler() {
    }

    public byte[] downscaleImage(byte[] image) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage bImage = ImageIO.read(in);
        if(bImage == null) {
            in.close();
            return image;
        }

        int w = bImage.getWidth();
        int h = bImage.getHeight();

        if(w <= MAX_WIDTH && h <= MAX_HEIGHT) return image;

        if(w > MAX_WIDTH){
            // Get ratio for scaling image
            float ratio = (float) MAX_WIDTH / w;
            // Scale image dimensions
            h = (int) (h * ratio);
            w = (int) (w * ratio);
        }

        if(h > MAX_HEIGHT){
            float ratio = MAX_HEIGHT / h;

            w = (int) (w * ratio);
            h = (int) (h * ratio);
        }

        BufferedImage nImage =
                Scalr.resize(bImage, Scalr.Method.QUALITY,w, h);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(nImage, "jpg", baos);
        byte[] newImageByteArray = baos.toByteArray();

        bImage.flush();
        in.close();
        baos.close();

        return newImageByteArray;
    }
}
