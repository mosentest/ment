package hemu.ment.core.listener;

import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.constant.ApplicationVariable;
import hemu.ment.core.constant.SupportedConstant;
import hemu.ment.core.ejb.local.EnterpriseSettingsLocal;
import hemu.ment.core.entity.Enterprise;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by muu on 2015/5/27.
 */
public class ContextListener implements ServletContextListener {

    @EJB
    private EnterpriseSettingsLocal enterpriseSettingsEJB;

    @Inject
    private CacheConsole cacheConsole;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        //add enterprise settings
        List<Enterprise> enterpriseList = enterpriseSettingsEJB.getAllSettings();
        for (Enterprise enterprise : enterpriseList) {
            cacheConsole.appCache(ApplicationVariable.I18N + enterprise.getCode(), enterprise.getInternationalizationSettings());
            cacheConsole.appCache(ApplicationVariable.EMAIL + enterprise.getCode(), enterprise.getEmailSettings());
            cacheConsole.appCache(ApplicationVariable.GLOBAL + enterprise.getCode(), enterprise.getGlobalSettings());
        }
        //add supported constants
        context.setAttribute("SUPPORTED_LOCALE", SupportedConstant.SUPPORTED_LOCALE);
        context.setAttribute("SUPPORTED_TIMEZONE", SupportedConstant.SUPPORTED_TIMEZONE);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
