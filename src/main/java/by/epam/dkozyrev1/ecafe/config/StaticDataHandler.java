package by.epam.dkozyrev1.ecafe.config;

import by.epam.dkozyrev1.ecafe.controller.servlet.CommonServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class StaticDataHandler {

    private static StaticDataHandler INSTANCE = null;
    private static Logger LOGGER = LogManager.getLogger(CommonServlet.class);

    public static StaticDataHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StaticDataHandler();
        }
        return INSTANCE;
    }

    private StaticDataHandler() {
    }

    public Logger getLOGGER() {
        return LOGGER;
    }

    public byte[] getHOME_ICON() {
        byte[] TEMP_IC;
        try {
            TEMP_IC = Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-home-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load home icon cause of " + ex);
            TEMP_IC = null;
        }
        return TEMP_IC;
    }

    public byte[] getPROFILE_ICON() {
        byte[] TEMP_IC;
        try {
            TEMP_IC = Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-profile-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load profile icon cause of " + ex);
            TEMP_IC = null;
        }
        return TEMP_IC;
    }

    public byte[] getLANG_ICON() {
        byte[] TEMP_IC;
        try {
            TEMP_IC = Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-language-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load lang icon cause of " + ex);
            TEMP_IC = null;
        }
        return TEMP_IC;
    }

    public byte[] getADDING_ICON() {
        byte[] TEMP_IC;
        try {
            TEMP_IC = Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\adding-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load adding icon cause of " + ex);
            TEMP_IC = null;
        }
        return TEMP_IC;
    }

    public byte[] getDELETING_ICON() {
        byte[] TEMP_IC;
        try {
            TEMP_IC = Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\deleting-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load deleting icon cause of " + ex);
            TEMP_IC = null;
        }
        return TEMP_IC;
    }

    public byte[] getCART_ICON() {
        byte[] TEMP_IC;
        try {
            TEMP_IC = Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-cart-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load cart icon cause of " + ex);
            TEMP_IC = null;
        }
        return TEMP_IC;
    }

    public byte[] getEDIT_ICON() {
        byte[] TEMP_IC;
        try {
            TEMP_IC = Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-edit-icon.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load edit icon cause of " + ex);
            TEMP_IC = null;
        }
        return TEMP_IC;
    }

    public byte[] getBACKGROUND_PICTURE() {
        byte[] TEMP_IC;
        try {
            TEMP_IC = Files.readAllBytes(Paths.get("C:\\Users\\danko\\IdeaProjects\\cafe\\src\\main\\webapp\\images\\ecafe-background.png"));
        } catch (IOException ex) {
            LOGGER.error("Could not load adding icon cause of " + ex);
            TEMP_IC = null;
        }
        return TEMP_IC;
    }

    @Override
    public String toString() {
        return "StaticDataHandler{" +
                "LOGGER=" + LOGGER +
                '}';
    }
}