package com.finalProject.demo.models.Users;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThirdParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ThirdPartyId;

    private String hashedKey;

    private String name;

    //Constructors

    public ThirdParty() {
    }

    public ThirdParty(String hashedKey, String name) {
        this.hashedKey = hashedKey;
        this.name = name;
    }

    //Getters & Setters
    public Long getThirdPartyId() {
        return ThirdPartyId;
    }

    public void setThirdPartyId(Long thirdPartyId) {
        ThirdPartyId = thirdPartyId;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
