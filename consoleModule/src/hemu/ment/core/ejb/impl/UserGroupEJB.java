package hemu.ment.core.ejb.impl;

import hemu.ment.core.ejb.local.UserGroupLocal;
import hemu.ment.core.entity.User;
import hemu.ment.core.entity.UserGroup;
import hemu.ment.core.enums.RoleConstant;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by muu on 2015/5/30.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UserGroupEJB implements UserGroupLocal {

	@PersistenceContext(unitName = "ment_core")
	private EntityManager entityManager;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<UserGroup> getList(Long enterprise) {
		Query query = entityManager.createNamedQuery("UserGroup.GetList");
		query.setParameter("enterprise", enterprise);
		List<UserGroup> list = query.getResultList();
		for (UserGroup userGroup : list) {
			userGroup.setRoleConstant(RoleConstant.getRoleConstant(userGroup.getRole()));
			userGroup.getUsers().size();
		}
		return list;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UserGroup getUserGroup(Long enterprise, String role) {
		Query query = entityManager.createNamedQuery("UserGroup.Get");
		query.setParameter("enterprise", enterprise);
		query.setParameter("role", role);
		List<UserGroup> list = query.getResultList();
		if (list.size() == 0) {
			return null;
		}
		UserGroup userGroup = list.get(0);
		userGroup.setRoleConstant(RoleConstant.getRoleConstant(userGroup.getRole()));
		userGroup.getUsers().size();
		return userGroup;
	}

	@Override
	public UserGroup update(UserGroup userGroup) {
		return null;
	}

	@Override
	public void remove(UserGroup userGroup) {

	}

	@Override
	public UserGroup create(UserGroup userGroup) {
		return null;
	}
}
