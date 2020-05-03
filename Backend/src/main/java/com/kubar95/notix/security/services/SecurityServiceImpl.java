package com.kubar95.notix.security.services;


import com.kubar95.notix.domain.OwnableEntity;
import com.kubar95.notix.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public <T extends OwnableEntity> boolean hasAccessToEntity(T entity) {
        Long ownerId = entity.getOwner().getId();
        return getUserId().equals(ownerId);
    }

    @Override
    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getId();
    }
}
