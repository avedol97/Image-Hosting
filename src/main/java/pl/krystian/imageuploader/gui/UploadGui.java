package pl.krystian.imageuploader.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.krystian.imageuploader.ImageUpader;

@Route("upload")
public class UploadGui extends VerticalLayout {

    private ImageUpader imageUpader;

    @Autowired
    public UploadGui(ImageUpader imageUpader) {
        this.imageUpader = imageUpader;

        Label label = new Label();
        TextField textField = new TextField();
        Button button = new Button("Upload");

        add(textField);
        add(button);

        button.addClickListener(clickEvent ->
        {
           String uploadedImage =  imageUpader.uploadFileAndSaveToDb(textField.getValue());
            Image image = new Image(uploadedImage,"No Image ;(");
            label.setText("Congratulations, you uploaded image!");
            add(label);
            add(image);
        });


    }
}
