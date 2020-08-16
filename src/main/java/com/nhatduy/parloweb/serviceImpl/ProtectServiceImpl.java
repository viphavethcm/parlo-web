package com.nhatduy.parloweb.serviceImpl;

import com.nhatduy.parloweb.entity.AuthRequest;
import com.nhatduy.parloweb.service.ProtectService;

public class ProtectServiceImpl implements ProtectService {
    @Override
    public boolean SQL_Injection(AuthRequest authRequest) {
        if (authRequest.getUserName().contains("'or'") || authRequest.getPassword().contains("''"))
            return true;
        return false;
    }
}
