package hemu.ment.resource.ejb.local;

import hemu.ment.core.entity.Enterprise;

import javax.ejb.Local;

/**
 * Created by muu on 2015/6/13.
 */
@Local
public interface ResourceLocal {

	public boolean userAccessible(long user, long enterprise);

	public Enterprise getUserEnterprise(long user);

}
