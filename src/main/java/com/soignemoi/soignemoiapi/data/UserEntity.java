package com.soignemoi.soignemoiapi.data;

import com.soignemoi.soignemoiapi.data.values.Role;

public abstract class UserEntity {

    public abstract String getUsername();
    public abstract String getPassword();
    public abstract Role getRole();

    public UserEntity getAsUserEntity() { return this; }

}
