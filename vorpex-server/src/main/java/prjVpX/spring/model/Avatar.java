/*******************************************************************************
 * <copyright file="Avatar.java" company="VorpeX">
 *  Copyright (c) 2011-2012 All Right Reserved, http://prjvpx.biz/
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
 *  @email d.gunn@prjvpx.biz
 *  @date 18-12-2012
 *  @summary
 ******************************************************************************/
package prjVpX.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dominic
 * The model class for a users Avatar
 */

@Entity
@Table(name = "users")
public class Avatar implements Serializable {

	/**
	 * Generated Serial ID.
	 */
	private static final long serialVersionUID = 1786414934984938522L;

	/**
	 * ID of the Avatar.
	 */
	@Id
	@Column(name = "Avatar_id")
	private int iAvatarID;

	/**
	 * Name of the Avatar.
	 */
	@Column(name =  "Avatar_name")
	private String sAvatarName;

	/**
	 * Empty constructor for 'Avatar' model.
	 */
	public Avatar() {

	}

	/**
	 * Default constructor for 'Avatar' model.
	 * @param iUserID contains the ID of the users avatar
	 * @param sUsername contains the name of the persons avatar.
	 */
	public Avatar(final int iUserID, final String sUsername) {

		this.iAvatarID = iUserID;
		this.sAvatarName = sUsername;
	}

	/**
	 * Function used to get an Avatar id.
	 * @return the id of an Avatar
	 */
	public final int getAvatarID() {

		return this.iAvatarID;
	}

	/**
	 * Function used to set an Avatar id.
	 * @param iID the id to be set
	 */
	public final void setAvatarID(final int iID) {

		this.iAvatarID = iID;
	}

	/**
	 * Function used to get an Avatar name.
	 * @return the name of an Avatar
	 */
	public final String getAvatarName() {

		return this.sAvatarName;
	}

	/**
	 * Function used to set an Avatar name.
	 * @param sName the name to be set.
	 */
	public final void setAvatarID(final String sName) {

		this.sAvatarName = sName;
	}

	/**
	 * Grabs the unique serial id for this class.
	 * @return Returns the serial id.
	 */
	@Override
	public final int hashCode() {

		return (int) Avatar.serialVersionUID;
	}
}
