/**
 * Copyright 2005 CANTV. All rights reserved.
 * CANTV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * This software is the confidential and proprietary information
 * of CANTV. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with CANTV.
 *
 * Creation date: 2005-09-14
 *
 */
package com.acme.rpg.util.logging;

import java.io.Serializable;

/**
 * Interface ContextInformation
 *
 * This interface is intended to model the user context information as login, sessionID, etc. 
 * This context information is vital to identify any user specific trace in the log files. 
 *
 * @author Liodegar Bracamonte
 * @see Serializable interface
 */
public interface ContextInformation extends Serializable {	
	
	
	/**
	 * Returns a correlative user code. This code must identify in a unique way one request from user, 
	 * therefore, this code must be a concatenation of different values, as login, timestamp, sessionID, etc.
	 * @return a String containing the correlative user code.
	 */
	String getCorrelativeUserCode();
	

}
