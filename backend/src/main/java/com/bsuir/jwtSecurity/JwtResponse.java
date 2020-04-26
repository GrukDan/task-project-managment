package com.bsuir.jwtSecurity;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwttoken;
    private final String userName;
    private final String userSurname;
    private final long idUser;

    public JwtResponse(String jwttoken, String userName, String userSurname, long idUser) {
        this.jwttoken = jwttoken;
        this.userName = userName;
        this.userSurname = userSurname;
        this.idUser = idUser;
    }


    public String getJwttoken() {
        return jwttoken;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public long getIdUser() {
        return idUser;
    }
}
