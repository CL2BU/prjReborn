/*******************************************************************************
 * <copyright file="AvatarManagerImpl.java" company="VorpeX">
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
package vorpex.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import vorpex.spring.dao.IAvatarDAO;

@Service(value="avatarManager")
public class AvatarManagerImpl implements IAvatarManager {

	@Resource(name="avatarDAO")
	private IAvatarDAO avatarDAO = null;

	/**
	 * Used to get the DAO of the Avatar model.
	 * @return the DAO of the Avatar Model
	 */
	public final IAvatarDAO getAvatarDAO() {

		return avatarDAO;
	}

	/**
	 * Used to set the DAO of the Avatar Model.
	 * @param avatarDAO the DAO being set.
	 */
	public void setAvatarDAO(IAvatarDAO avatarDAO){

		this.avatarDAO = avatarDAO;
	}
}
