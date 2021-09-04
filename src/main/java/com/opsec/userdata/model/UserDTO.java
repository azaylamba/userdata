package com.opsec.userdata.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;


/**
 * Class to pass user information through end points. <br>
 * The information from this object will then be mapped to {@link User} object in {@link com.opsec.userdata.controller.UserController}.
 */
public class UserDTO {
    
    @ApiModelProperty(notes = "The specified user id")
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
     * Gets id of the userDTO.
     *
     * @return id of the user
     */
    public long getId() {
        return id;
    }
    
    /**
     * Sets id on the userDTO object.
     *
     * @param id user id
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Returns first name of the userDTO.
     *
     * @return first name of the user
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Sets first name of the userDTO.
     *
     * @param firstName first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Returns surname of the userDTO.
     *
     * @return surname of the user
     */
    public String getSurname() {
        return surname;
    }
    
    /**
     * Sets surname on the userDTO.
     *
     * @param surname surname of the user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * Returns date of birth for the userDTO.
     *
     * @return DOB of the user
     */
    public Date getDob() {
        return dob;
    }
    
    /**
     * Sets date of birth on the userDTO object.
     *
     * @param dob DOB of the user
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    /**
     * Returns title of the userDTO.
     *
     * @return title of the user
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets title on the userDTO.
     *
     * @param title title of the user
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
}
