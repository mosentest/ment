
package hemu.ment.core.ejb.impl;

import hemu.ment.core.ejb.local.UserLocal;
import hemu.ment.core.ejb.remote.UserRemote;
import hemu.ment.core.entity.User;
import hemu.ment.core.exception.InformationException;
import hemu.ment.core.utility.EncryptionUtil;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UserEJB implements UserLocal, UserRemote {

	@PersistenceContext(unitName = "ment_core")
	private EntityManager entityManager;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User login(String email, String password) throws InformationException {
        Query query = entityManager.createNamedQuery("User.GetByEMail");
        query.setParameter("email", email);
        List result = query.getResultList();
        if (result == null || result.size() == 0) {
            throw new InformationException("Account does not exist");
        }
        User user = (User) result.get(0);
        if (!user.isEnabled()) {
            throw new InformationException("User is not activated");
        }
        try {
            String decryptedPassword = EncryptionUtil.decrypt(user.getHash(), user.getPassword());
            if (!decryptedPassword.equals(password)) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new InformationException("Password is incorrect");
        }
        user.getEnterprise().getId();
        user.setLogin(new Date());
        entityManager.persist(user);
        return user;
    }
	
}
