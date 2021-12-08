package com.processor.service;

import com.processor.entity.AdminDO;

public interface AdminService {
    public boolean validateAdmin (AdminDO adminDO);
    public boolean insertAdmin (AdminDO adminDO);

}
