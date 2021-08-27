package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.command.Command;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class GetLocalImage extends Command {


    public GetLocalImage(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() {
        try(OutputStream os = getResponse().getOutputStream()){
            os.write(switch (getRequest().getParameter("key")) {
                case "home" -> StaticDataHandler.INSTANCE.getHOME_ICON();
                case "profile" -> StaticDataHandler.INSTANCE.getPROFILE_ICON();
                case "lang" -> StaticDataHandler.INSTANCE.getLANG_ICON();
                case "adding" -> StaticDataHandler.INSTANCE.getADDING_ICON();
                case "deleting" -> StaticDataHandler.INSTANCE.getDELETING_ICON();
                case "cart" -> StaticDataHandler.INSTANCE.getCART_ICON();
                case "edit" -> StaticDataHandler.INSTANCE.getEDIT_ICON();
                case "background" -> StaticDataHandler.INSTANCE.getBACKGROUND_PICTURE();
                default -> throw new IllegalStateException("Unexpected value: " + getRequest().getParameter("imgName"));
            });
            os.flush();
        } catch (IOException ex){
            StaticDataHandler.INSTANCE.getLOGGER().error(ex);
        }
    }

    @Override
    public void executePost() {
        throw new UnsupportedOperationException();
    }

}
