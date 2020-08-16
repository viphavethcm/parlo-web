package com.nhatduy.parloweb.service;

import com.nhatduy.parloweb.entity.AuthRequest;

public interface ProtectService {

    public boolean SQL_Injection(AuthRequest AuthRequest);
}
