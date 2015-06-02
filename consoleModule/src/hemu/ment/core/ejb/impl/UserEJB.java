
package hemu.ment.core.ejb.impl;

import hemu.ment.core.ejb.local.UserLocal;
import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.User;
import hemu.ment.core.entity.UserGroup;
import hemu.ment.core.enums.RoleConstant;
import hemu.ment.core.exception.InformationException;
import hemu.ment.core.query.Page;
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
public class UserEJB implements UserLocal {

	@PersistenceContext(unitName = "ment_core")
	private EntityManager entityManager;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User login(String email, String password) throws InformationException {
        Query query = entityManager.createNamedQuery("User.GetByEmail");
        query.setParameter("email", email);
        List<User> result = query.getResultList();
        if (result == null || result.size() == 0) {
            throw new InformationException("Account does not exist");
        }
        User user = result.get(0);
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
        user.getUserGroups().size();
        user.getEnterprise().getId();
        user.setLogin(new Date());
        for (UserGroup userGroup : user.getUserGroups()) {
            user.getRoles().add(RoleConstant.getRoleConstant(userGroup.getRole()));
        }
        entityManager.persist(user);
        return user;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean accessible(Long user, Long enterprise) {
        Query query = entityManager.createNamedQuery("User.Accessible");
        query.setParameter("user", user);
        query.setParameter("enterprise", enterprise);
        return (boolean) query.getSingleResult();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Enterprise getEnterprise(Long user) {
        Query query = entityManager.createNamedQuery("User.GetEnterprise");
        query.setParameter("user", user);
        return (Enterprise) query.getSingleResult();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User create(User user) {
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Page<User> list(Long enterprise, String queryString, int page, int size) {
        Page<User> collection = new Page<>();
        Query query = entityManager.createNamedQuery("User.Size");
        query.setParameter("enterprise", enterprise);
        long total = (long) query.getSingleResult();
        if (total != 0) {
            collection.setCurrentPage(page);
            collection.setTotalElement(total, size);

            query = entityManager.createNamedQuery("User.List");
            query.setParameter("enterprise", enterprise);
            query.setFirstResult(page * size);
            query.setMaxResults(size);
            collection.setContent(query.getResultList());
            if (collection.getContent().size() == 0) {
                collection.setTotalElement(0, size);
            }
        }
        return collection;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User get(Long enterprise, Long user) {
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User update(User user) {
        return null;
    }
}
