package hemu.ment.core.ejb.local;

import hemu.ment.core.entity.UserGroup;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by muu on 2015/5/30.
 */
@Local
public interface UserGroupLocal {

    public List<UserGroup> getList(Long enterprise);

    public UserGroup getUserGroup(Long userGroup);

    public UserGroup update(UserGroup userGroup);

    public void remove(UserGroup userGroup);

    public UserGroup create(UserGroup userGroup);

}
