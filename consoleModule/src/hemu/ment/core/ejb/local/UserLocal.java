package hemu.ment.core.ejb.local;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;
import hemu.ment.core.exception.InformationException;
import hemu.ment.core.query.Order;
import hemu.ment.core.query.Page;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserLocal {

    public User login(String email, String password) throws InformationException;

    public boolean accessible(Long user, Long enterprise);

    public Enterprise getEnterprise(Long user);

    public User create(User user);

    public Page<User> list(Long enterprise, String query, int page, int size);

    public User get(Long enterprise, Long user) throws InformationException;

    public User update(User user);

}
