package com.opsec.userdata.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * User domain class to represent User information.
 *
 */
@Document(collection = "user")
public class User {
    
    @ApiModelProperty(notes = "The specified user id")
    @Id
    private long id;
    @ApiModelProperty(notes = "First name of the user")
    private String firstName;
    @ApiModelProperty(notes = "Surname of the user")
    private String surname;
    @ApiModelProperty(notes = "DOB of the user")
    private Date dob;
    @ApiModelProperty(notes = "Title of the user")
    private String title;
    
    /**
     * Gets id of the user.
     *
     * @return Id of the user
     */
    public long getId() {
        return id;
    }
    
    /**
     * Sets id on the user object.
     *
     * @param id Id for the user
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Returns first name of the user.
     *
     * @return First name of the user
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Sets first name of the user.
     *
     * @param firstName first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Returns surname of the username.
     *
     * @return surname of the user
     */
    public String getSurname() {
        return surname;
    }
    
    /**
     * Sets surname on the user.
     *
     * @param surname surname of the user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * Returns date of birth for the user.
     *
     * @return DOB of the user
     */
    public Date getDob() {
        return dob;
    }
    
    /**
     * Sets date of birth on the user object.
     *
     * @param dob DOB of the user
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    /**
     * Returns title of the user.
     *
     * @return title of the user
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets title on the user.
     *
     * @param title title of the user
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.dob.equals(user.getDob())
                && this.firstName.equals(user.getFirstName())
                && this.surname.equals(user.getSurname())
                && this.title.equals(user.getTitle())
                && this.id == user.getId();
    }
}
