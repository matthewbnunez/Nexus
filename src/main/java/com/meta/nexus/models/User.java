package com.meta.nexus.models;


import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Username is required!")
    @Size(min=3, max=30, message="Username must be between 3 and 30 characters")
    private String userName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;
    
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Share> shares;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "received_shares", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "share_id")
    )
    private List<Share> received_shares;
    

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "comments", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "share_id")
    )
    private List<Share> categories;
    
//   for Replies
    
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//        name = "reply", 
//        joinColumns = @JoinColumn(name = "user_id"), 
//        inverseJoinColumns = @JoinColumn(name = "share_id")
//    )
//    private List<Share> replies;
    
  
    public User() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirm() {
		return confirm;
	}


	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}


	public List<Share> getShares() {
		return shares;
	}


	public void setShares(List<Share> shares) {
		this.shares = shares;
	}


	public List<Share> getReceived_shares() {
		return received_shares;
	}


	public void setReceived_shares(List<Share> received_shares) {
		this.received_shares = received_shares;
	}


	public List<Share> getCategories() {
		return categories;
	}


	public void setCategories(List<Share> categories) {
		this.categories = categories;
	}
    
    



    
    
}
    
