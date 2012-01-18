/*******************************************************************************
 * <copyright file="HibernateAvatarDAO.java" company="VorpeX">
 *  Copyright (c) 2011-2012 All Right Reserved, http://vorpex.biz/
 *  
 *  This source is subject to the "Don't Be A Dick" License.
 *  Please see the License.txt file for more information.
 *  You may not use this file except in compliance with the License.
 *  
 *  THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
 *  KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 *  PARTICULAR PURPOSE.
 *  
 *  @author Dominic Gunn
 *  @email d.gunn@vorpex.biz
 *  @date 18-12-2012
 *  @summary
 ******************************************************************************/
package vorpex.spring.hibernatedao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import vorpex.spring.dao.IAvatarDAO;
import vorpex.spring.model.Avatar;

@Service(value="avatarDAO")
public class HibernateAvatarDAO extends HibernateDaoSupport implements IAvatarDAO {

	@Autowired
	public HibernateAvatarDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory){
		
		this.setSessionFactory(sessionFactory);
	}
	
	/**
	 * Used to save or update any changes made to a users Avatar
	 * @param avatar the instance of Avatar to be updated.
	 */
	public void saveOrUpdate(Avatar avatar) {
		
		this.getHibernateTemplate().save(avatar);
	}

	/**
	 * Used to find a particular users avatar.
	 * @param id the ID of the users avatar
	 * @return an instance of the users avatar.
	 */
	public Avatar getAvatarByID(int id) {
		
		return this.getHibernateTemplate().load(Avatar.class, id);
	}
}
