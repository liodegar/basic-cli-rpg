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

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Interface ContextInformationBean
 *
 * This class encapsulates the user context information. This class is suitable for web enabled apps, 
 * because stores the HttpSession.getId() and the ServletRequest.getRemoteHost() fields. 
 * 
 * This class is immutable (is not possible modify its internal state after creation object) because
 * is desirable to avoid inter-tier changes executed by the user.
 *
 * @author Liodegar Bracamonte
 * @see ContextInformation interface
 */
public final class ContextInformationBean implements ContextInformation {

	private static final String UNDERSCORE = "_";
	/**
	 * Unique identifier assigned by the app server to this user session
	 */
	private final String sessionID;
	
	/**
	 * Login used by the user to enter in the system.
	 */
	private final String login;
	
	/**
	 * The difference, measured in milliseconds, between the current time and midnight, January 1, 1970 UTC
	 */
	private long timestamp;

	/**
	 * The local host name
	 */
	private final String localHost;

	/**
	 * The fully qualified name of the client that sent the request.
	 */
	private final String remoteHost;

	/**
	 * The user's group which the user belongs.
	 */
	private final String userGroup;
	
	/**
	 * Determines if a de-serialized file is compatible with this class.
	 */
	private static final long serialVersionUID = 398167139909127855L;

	/**
	 * Creates a new ContextInformationBean
	 */
	public ContextInformationBean() {
		this(null);
	}

	/**
	 * Creates a new ContextInformationBean using the given parameter.
	 * @param login Login used by the user to enter in the system.
	 */
	public ContextInformationBean(String login) {
		this(null, login);
	}

	/**
	 * Creates a new ContextInformationBean using the given parameters.
	 * @param sessionID Unique identifier assigned by the app server to this user session
	 * @param login Login used by the user to enter in the system.
	 */
	public ContextInformationBean(String sessionID, String login) {
		this(sessionID, login, null);
	}

	/**
	 * Creates a new ContextInformationBean using the given parameters.
	 * @param sessionID Unique identifier assigned by the app server to this user session
	 * @param login Login used by the user to enter in the system.
	 * @param remoteHost The fully qualified name of the client that sent the request.
	 */
	public ContextInformationBean(String sessionID, String login, String remoteHost) {
		this(sessionID, login, remoteHost, null);
	}

	/**
	 * Creates a new ContextInformationBean using the given parameters.
	 * @param sessionID Unique identifier assigned by the app server to this user session
	 * @param login Login used by the user to enter in the system.
	 * @param remoteHost The fully qualified name of the client that sent the request.
	 * @param userGroup The user's group which the user belongs.
	 */
	public ContextInformationBean(String sessionID, String login, String remoteHost, String userGroup) {
		this.sessionID = sessionID;
		this.login = login;
		this.timestamp = System.currentTimeMillis();
		this.localHost = retrieveLocalHostName();
		this.remoteHost = remoteHost;
		this.userGroup = userGroup;
	}

	/**
	 * Returns the unique identifier assigned to this user session.
	 * @return a string specifying the identifier assigned to this session.
	 */
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * Returns the login used by the user to enter in the system.
	 * @return a String containing the user's login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Returns the current time in milliseconds.
	 * @return the difference, measured in milliseconds, between
	 *          the current time and midnight, January 1, 1970 UTC.
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Returns the local host name.
	 * @return a String containing the  the local host name.
	 */
	public String getLocalHost() {
		return localHost;
	}

	/**
	 * Returns the fully qualified name of the client that sent the request.
	 * @return a String containing the fully qualified name of the client
	 */
	public String getRemoteHost() {
		return remoteHost;
	}
	

	/**
	 * Returns the user's group which the user belongs.
	 * @return a String containing the user's group.
	 */
	public String getUserGroup() {
		return userGroup;
	}

	/**
	 * Returns a correlative user code.
	 * @return The concatenation of sessionID, login and the timestamp in the following way:
	 * sessionID_login_timestamp.
	 */
	public String getCorrelativeUserCode() {
		StringBuffer result = new StringBuffer(200);
		result.append(getSessionID()).append(UNDERSCORE).append(getLogin());
		result.append(UNDERSCORE).append(getTimestamp());
		return result.toString();
	}

	/**
	 * Retrieves the local host name.
	 * @return the local host name if this can retrieved, otherwise returns null.
	 */
	private final String retrieveLocalHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return null;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContextInformationBean that = (ContextInformationBean) o;

		if (timestamp != that.timestamp) return false;
		if (sessionID != null ? !sessionID.equals(that.sessionID) : that.sessionID != null) return false;
		if (login != null ? !login.equals(that.login) : that.login != null) return false;
		if (localHost != null ? !localHost.equals(that.localHost) : that.localHost != null) return false;
		if (remoteHost != null ? !remoteHost.equals(that.remoteHost) : that.remoteHost != null) return false;
		return !(userGroup != null ? !userGroup.equals(that.userGroup) : that.userGroup != null);

	}

	@Override
	public int hashCode() {
		int result = sessionID != null ? sessionID.hashCode() : 0;
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
		result = 31 * result + (localHost != null ? localHost.hashCode() : 0);
		result = 31 * result + (remoteHost != null ? remoteHost.hashCode() : 0);
		result = 31 * result + (userGroup != null ? userGroup.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ContextInformationBean{");
		sb.append("sessionID='").append(sessionID).append('\'');
		sb.append(", login='").append(login).append('\'');
		sb.append(", timestamp=").append(timestamp);
		sb.append(", localHost='").append(localHost).append('\'');
		sb.append(", remoteHost='").append(remoteHost).append('\'');
		sb.append(", userGroup='").append(userGroup).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
