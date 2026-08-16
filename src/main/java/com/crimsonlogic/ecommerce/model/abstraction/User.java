package com.crimsonlogic.ecommerce.model.abstraction;

import com.crimsonlogic.ecommerce.enumeration.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract superclass for every user in the marketplace.
 *
 * <p>
 * Admin, Seller and Customer inherit all common
 * properties from this class.
 * </p>
 */
public abstract class User {

	/* User ID */
	private String userId;

	/* User Name */
	private String userName;

	/* User Email */
	private String userEmail;

	/* User Phone Number */
	private String userPhNo;

	/* User Password */
	private String userPassword;

	/* User Role */
	private Role role;


	/**
	 * Default Constructor.
	 */
	public User() {

	}


	/**
	 * Parameterized Constructor.
	 *
	 * @param userId       User ID
	 * @param userName     User Name
	 * @param userEmail    User Email
	 * @param userPhNo     User Phone Number
	 * @param userPassword User Password
	 */
	public User(
			String userId,
			String userName,
			String userEmail,
			String userPhNo,
			String userPassword) {

		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhNo = userPhNo;
		this.userPassword = userPassword;
	}


	// ==========================================================
	// Getters & Setters
	// ==========================================================

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPhNo() {
		return userPhNo;
	}

	public void setUserPhNo(String userPhNo) {
		this.userPhNo = userPhNo;
	}


	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public Role getRole() {
		return role;
	}

	/**
	 * Role is assigned by child classes.
	 */
	public void setRole(Role role) {
		this.role = role;
	}


	// ==========================================================
	// Object Methods
	// ==========================================================

	/**
	 * Two Users are equal if their User IDs are equal.
	 */
	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		User other = (User) object;

		return Objects.equals(userId, other.userId);
	}


	/**
	 * HashCode based on User ID.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}


	/**
	 * Returns common User information.
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("\nUser ID      : ")
				.append(userId)
				.append("\nRole         : ")
				.append(role)
				.append("\nName         : ")
				.append(userName)
				.append("\nEmail        : ")
				.append(userEmail)
				.append("\nPhone Number : ")
				.append(userPhNo);

		return builder.toString();
	}


	/**
	 * Returns common user details in table format.
	 *
	 * @return Profile rows.
	 */
	public List<String[]> getTableRows() {

		List<String[]> rows = new ArrayList<>();

		rows.add(
				new String[]{
						"User ID",
						getUserId()
				});

		rows.add(
				new String[]{
						"Name",
						getUserName()
				});

		rows.add(
				new String[]{
						"Email",
						getUserEmail()
				});

		rows.add(
				new String[]{
						"Phone Number",
						getUserPhNo()
				});

		rows.add(
				new String[]{
						"Role",
						getRole().name()
				});

		return rows;
	}
}