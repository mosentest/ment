package hemu.ment.core.ejb.remote;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by muu on 2015/5/25.
 */
@Remote
public interface EnterpriseRemote {

    public Enterprise create(Enterprise enterprise);

    public Enterprise update(Enterprise enterprise);

    public void remove(Long id);

    public Enterprise get(Long id);

    public List<Enterprise> getList(int offset, int size);


}
