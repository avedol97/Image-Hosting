package pl.krystian.imageuploader;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krystian.imageuploader.Model.Image;
import pl.krystian.imageuploader.repo.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUpader {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    @Autowired
    public ImageUpader(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwk79hhkf",
                "api_key", "219155461194715",
                "api_secret", "ZaGfdqkuwhtfRV94lJuXF9awAZ0"));
    }


    public String uploadFileAndSaveToDb(String path){
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            System.out.println("no-good");
        }
        return uploadResult.get("url").toString();
    }

}


