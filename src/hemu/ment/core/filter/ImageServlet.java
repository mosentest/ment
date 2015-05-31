package hemu.ment.core.filter;

import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.constant.ApplicationVariable;
import hemu.ment.core.ejb.local.UserLocal;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.utility.ContextUtil;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by muu on 2015/5/31.
 */
@WebServlet("/image/profile/*")
public class ImageServlet extends HttpServlet {

    @EJB
    private UserLocal userEJB;

    @Inject
    private CacheConsole cacheConsole;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enterprise enterprise = ContextUtil.getEnterprise(request);
        String fileName = request.getPathInfo().substring(1);
        if (fileName.equals("default.png")) {
            byte[] array = cacheConsole.appCacheByteArray("default-profile");
            response.setHeader("Content-Length", String.valueOf(array.length));
            response.setHeader("Content-Type", getServletContext().getMimeType(fileName));
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
            response.getOutputStream().write(array);
        } else {
            Long id = Long.parseLong(fileName.substring(0, fileName.indexOf('.')));
            if (enterprise.isMaster() || userEJB.accessible(id, enterprise.getId())) {
                Enterprise userEnterprise = userEJB.getEnterprise(id);
                File file = new File(ApplicationVariable.IMAGE_PATH.replace("{enterprise}", userEnterprise.getCode()), fileName);
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Type", getServletContext().getMimeType(fileName));
                response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
                Files.copy(file.toPath(), response.getOutputStream());
            }
        }
    }

}
