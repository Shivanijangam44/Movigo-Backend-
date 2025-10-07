package com.movigo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.movigo.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
    @Column(nullable = false)
	private String name;
	
    @Column(nullable = false , unique = true)
	private String email;
	
    @Column(nullable = false)
	private String password;
	
    @Column(length = 10, nullable = false)
	private String mobileNumber;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
	
	
}
