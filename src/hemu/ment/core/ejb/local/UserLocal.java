package hemu.ment.core.ejb.local;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;
import hemu.ment.core.exception.InformationException;

import javax.ejb.Local;

@Local
public interface UserLocal {

    public User login(String email, String password) throws InformationException;

    public boolean accessible(Long user, Long enterprise);

    public Enterprise getEnterprise(Long user);

}
