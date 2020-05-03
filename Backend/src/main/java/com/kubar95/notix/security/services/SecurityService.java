package com.kubar95.notix.security.services;

import com.kubar95.notix.domain.OwnableEntity;
import org.springframework.stereotype.Service;


@Service
public interface SecurityService {
   <T extends OwnableEntity> boolean hasAccessToEntity(T entity);
   Long getUserId();
}
