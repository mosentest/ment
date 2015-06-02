
import hemu.ment.core.ejb.remote.UserRemote;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println(doLookup().toString());
	}

	private static UserRemote doLookup() {
		Context context = null;
		UserRemote bean = null;
		try {
			// 1. Obtaining Context
			context = getInitialContext();
			// 2. Lookup and cast
			bean = (UserRemote) context.lookup("ment/UserEJB!hemu.ment.core.ejb.remote.UserRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}

	private static Context initialContext;

	public static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			// Properties extends HashTable
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jboss.naming.remote.client.InitialContextFactory");
			prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			prop.put(Context.PROVIDER_URL, "remote://localhost:4447");
			prop.put(Context.SECURITY_PRINCIPAL, "muu");
			prop.put(Context.SECURITY_CREDENTIALS, "Hyrac0therium_");
			prop.put("jboss.naming.client.ejb.context", true);
			initialContext = new InitialContext(prop);
		}
		return initialContext;
	}
}
