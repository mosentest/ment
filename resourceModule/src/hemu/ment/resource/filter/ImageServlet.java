package hemu.ment.resource.filter;

import hemu.ment.core.cache.CacheConsole;
import hemu.ment.core.constant.C;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.resource.ejb.local.ResourceLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by muu on 2015/6/13.
 */
public class ImageServlet extends HttpServlet {

	@EJB
	private ResourceLocal resourceEJB;

	@Inject
	private CacheConsole cacheConsole;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enterprise enterprise = cacheConsole.getEnterprise((String) request.getSession().getAttribute(C.AUTH_TOKEN));
		String fileName = request.getPathInfo().substring(1);
		if (fileName.equals("default.png")) {
			byte[] array = cacheConsole.getAppByteArray("default-profile");
			response.setHeader("Content-Length", String.valueOf(array.length));
			response.setHeader("Content-Type", getServletContext().getMimeType(fileName));
			response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
			response.getOutputStream().write(array);
		} else {
			Long id = Long.parseLong(fileName.substring(0, fileName.indexOf('.')));
			if (enterprise.isMaster() || resourceEJB.userAccessible(id, enterprise.getId())) {
				Enterprise userEnterprise = resourceEJB.getUserEnterprise(id);
				File file = new File(C.IMAGE_PATH.replace("{enterprise}", userEnterprise.getCode()), fileName);
				response.setHeader("Content-Length", String.valueOf(file.length()));
				response.setHeader("Content-Type", getServletContext().getMimeType(fileName));
				response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
				Files.copy(file.toPath(), response.getOutputStream());
			}
		}
	}

}
