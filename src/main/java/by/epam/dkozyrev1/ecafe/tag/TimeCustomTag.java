package by.epam.dkozyrev1.ecafe.tag;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCustomTag extends TagSupport {

    @Override
    public int doStartTag() {
        try {
            pageContext.getOut().print(new SimpleDateFormat("yyyy-MM-dd HH:mm Z").format(new Date()));
        } catch (IOException ex) {
            StaticDataHandler.getInstance().getLOGGER().error(ex);
        }
        return SKIP_BODY;
    }
}