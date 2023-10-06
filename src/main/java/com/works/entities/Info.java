package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Info {

    public Info() {
    }

    public Info(String url, String agent, String sessionID, String email, String time) {
        this.url = url;
        this.agent = agent;
        this.sessionID = sessionID;
        this.email = email;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    private String url;
    private String agent;
    private String sessionID;
    private String email;
    private String time;


}
