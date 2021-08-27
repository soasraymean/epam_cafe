package by.epam.dkozyrev1.ecafe.config;


import by.epam.dkozyrev1.ecafe.controller.servlet.CommonServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public enum  StaticDataHandler {

    INSTANCE;
    private final Logger LOGGER = LogManager.getLogger(CommonServlet.class);

    private final byte[] HOME_ICON;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-home-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load home icon cause of " + ex);
            TEMP_IC = null;
        }
        HOME_ICON = TEMP_IC;
    }
    private final byte[] LANG_ICON;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-language-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load lang icon cause of " + ex);
            TEMP_IC = null;
        }
        LANG_ICON = TEMP_IC;
    }
    private final byte[] CART_ICON;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-cart-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load cart icon cause of " + ex);
            TEMP_IC = null;
        }
        CART_ICON = TEMP_IC;
    }
    private final byte[] EDIT_ICON;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-edit-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load edit icon cause of " + ex);
            TEMP_IC = null;
        }
        EDIT_ICON = TEMP_IC;
    }
    private final byte[] PROFILE_ICON;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-profile-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load profile icon cause of " + ex);
            TEMP_IC = null;
        }
        PROFILE_ICON = TEMP_IC;
    }
    private final byte[] ADDING_ICON;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\adding-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load adding icon cause of " + ex);
            TEMP_IC = null;
        }
        ADDING_ICON = TEMP_IC;
    }
    private final byte[] DELETING_ICON;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\deleting-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load deleting icon cause of " + ex);
            TEMP_IC = null;
        }
        DELETING_ICON = TEMP_IC;
    }
    private final byte[] BACKGROUND_PICTURE;
    {
        byte[] TEMP_IC;
        try {
            TEMP_IC =  Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-background.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load adding icon cause of " + ex);
            TEMP_IC = null;
        }
        BACKGROUND_PICTURE = TEMP_IC;
    }


    public Logger getLOGGER() {
        return LOGGER;
    }

    public byte[] getHOME_ICON() {
        return HOME_ICON;
    }

    public byte[] getPROFILE_ICON() {
        return PROFILE_ICON;
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "LOGGER=" + LOGGER +
                ", HOME_ICON=" + Arrays.toString(HOME_ICON) +
                ", PROFILE_ICON=" + Arrays.toString(PROFILE_ICON) +
                '}';
    }

    public byte[] getLANG_ICON() {
        return LANG_ICON;
    }

    public byte[] getADDING_ICON() {
        return ADDING_ICON;
    }

    public byte[] getDELETING_ICON() {
        return DELETING_ICON;
    }

    public byte[] getCART_ICON() {
        return CART_ICON;
    }

    public byte[] getEDIT_ICON() {
        return EDIT_ICON;
    }

    public byte[] getBACKGROUND_PICTURE() {
        return BACKGROUND_PICTURE;
    }
}