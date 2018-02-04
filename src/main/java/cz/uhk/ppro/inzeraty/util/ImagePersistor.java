package cz.uhk.ppro.inzeraty.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class ImagePersistor {

    public static void saveImage(MultipartFile mpf, String imageUuid){

        try {
            byte[] imgOriginal = mpf.getBytes();
            byte[] imgDownscaled = ImageDownscaler.downscaleImage(imgOriginal);
            if(imgOriginal != null || imgDownscaled != null) {
                String uuid = imageUuid;
                File file = new File("/home/pb/Documents/Projects/inzeraty/src/main/webapp/resources/images/downscaled/"+uuid+".jpg");
                OutputStream out = new FileOutputStream(file);
                out.write(imgDownscaled);
                out.flush();
                file = new File("/home/pb/Documents/Projects/inzeraty/src/main/webapp/resources/images/original/"+uuid+".jpg");
                out= new FileOutputStream(file);
                out.write(imgOriginal);
                out.flush();
                out.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
