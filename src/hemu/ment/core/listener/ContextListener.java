package hemu.ment.core.listener;

import hemu.ment.core.constant.ApplicationVariable;
import hemu.ment.core.ejb.local.EnterpriseSettingsLocal;
import hemu.ment.core.entity.Enterprise;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by muu on 2015/5/27.
 */
public class ContextListener implements ServletContextListener {

    @EJB
    private EnterpriseSettingsLocal enterpriseSettingsEJB;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        List<Enterprise> enterpriseList = enterpriseSettingsEJB.getAllSettings();
        for (Enterprise enterprise : enterpriseList) {
            event.getServletContext().setAttribute(ApplicationVariable.I18N + enterprise.getCode(), enterprise.getInternationalizationSettings());
            event.getServletContext().setAttribute(ApplicationVariable.EMAIL + enterprise.getCode(), enterprise.getEmailSettings());
            event.getServletContext().setAttribute(ApplicationVariable.GLOBAL + enterprise.getCode(), enterprise.getGlobalSettings());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
