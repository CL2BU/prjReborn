/*******************************************************************************
 * <copyright file="Avatar.java" company="VorpeX">
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
package vorpex.spring.model;

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
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = 1786414934984938522L;

	@Id
	@Column(name = "Avatar_id")
	private int iAvatarID;
	
	@Column(name =  "Avatar_name")
	private String sAvatarName;
	
	/**
	 * Empty constructor for 'Avatar' model
	 */
	public Avatar() {}
	
	/**
	 * Default constructor for 'Avatar' model. 
	 * @param iUserID contains the ID of the users avatar
	 * @param sUsername contains the name of the persons avatar.
	 */
	public Avatar(int iUserID, String sUsername){
		
		this.iAvatarID = iUserID;
		this.sAvatarName = sUsername;
	}
	
	/**
	 * Function used to get an Avatar id
	 * @return the id of an Avatar
	 */
	public int getAvatarID(){
		
		return this.iAvatarID;
	}
	
	/**
	 * Function used to set an Avatar id
	 * @param iAvatarID the id to be set
	 */
	public void setAvatarID(int iAvatarID){
		
		this.iAvatarID = iAvatarID;
	}
	
	/**
	 * Function used to get an Avatar name
	 * @return the name of an Avatar
	 */
	public String getAvatarName(){
		
		return this.sAvatarName;
	}
	
	/**
	 * Function used to set an Avatar name
	 * @param sAvatarName the name to be set.
	 */
	public void setAvatarID(String sAvatarName){
		
		this.sAvatarName = sAvatarName;
	}
	
	/**
	 * Grabs the unique serial id for this class.
	 */
	@Override
	public int hashCode(){
		
		return (int)Avatar.serialVersionUID;
	}
}
