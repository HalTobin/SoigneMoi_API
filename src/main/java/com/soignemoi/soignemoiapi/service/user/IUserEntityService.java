package com.soignemoi.soignemoiapi.service.user;

import com.soignemoi.soignemoiapi.data.UserEntity;

public interface IUserEntityService {
    UserEntity findByIdentifier(String identifier);
}
