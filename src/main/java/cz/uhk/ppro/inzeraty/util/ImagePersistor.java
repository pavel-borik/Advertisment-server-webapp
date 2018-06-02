package cz.uhk.ppro.inzeraty.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class ImagePersistor {

    @Autowired
    private ImageDownscaler imageDownscaler;

    public ImagePersistor() {
    }

    public void saveImage(MultipartFile mpf, String imageUuid){

        try {
            byte[] imgOriginal = mpf.getBytes();
            byte[] imgDownscaled = imageDownscaler.downscaleImage(imgOriginal);
            if(imgOriginal != null || imgDownscaled != null) {
                String uuid = imageUuid;
                File file = new File("M:/Documents/Projects/Java/Advertisment-server-webapp/src/main/webapp/resources/images/downscaled/"+uuid+".jpg");
                OutputStream out = new FileOutputStream(file);
                out.write(imgDownscaled);
                out.flush();
                out.close();
                file = new File("M:/Documents/Projects/Java/Advertisment-server-webapp/src/main/webapp/resources/images/original/"+uuid+".jpg");
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
