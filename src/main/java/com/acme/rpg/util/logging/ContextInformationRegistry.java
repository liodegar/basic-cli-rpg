/**
 * Copyright 2005 CANTV. All rights reserved.
 * CANTV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * This software is the confidential and proprietary information
 * of CANTV. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with CANTV.
 *
 * Creation date: 2005-09-14 (Barquisimeto's birthday)
 *
 */
package com.acme.rpg.util.logging;

/**
 * Interface ContextInformationRegistry
 *
 * This class creates a context information-thread binding  for Hera logging.
 *
 * @author Liodegar Bracamonte
 * @see ContextInformation interface
 */
public final class ContextInformationRegistry {

	/**
	 * ThreadLocal object to associate ContextInformation objects with the running thread.
	 */
	private static final ThreadLocal THREAD_CONTEXT_INFORMATION = new ThreadLocal();

	/**
	 * To avoid the instantiation outside this class.
	 */
	private ContextInformationRegistry() {
	}

	/**
	 * Retrieves the ContextInformation associated with the current thread.
	 * @return the current thread's ContextInformation of this thread-local or null 
	 *         if there isn't associated value.
	 */
	public static ContextInformation getCurrentContextInformation() {
		return (ContextInformation) THREAD_CONTEXT_INFORMATION.get();
	}

	/**
	 * Associates a ContextInformation with the current thread.
	 * @param contextInformation The ContextInformation object to associate.
	 */
	public static void setCurrentContextInformation(ContextInformation contextInformation) {
		THREAD_CONTEXT_INFORMATION.set(contextInformation);
	}

	/**
	 * Handy method to disassociate the ContextInformation object with the current thread.
	 */
	public static void clearCurrentContextInformation() {
		setCurrentContextInformation(null);
	}

	
}
