package com.ply.service;

import com.ply.entity.Ldap;
import com.ply.mapper.LdapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ldapService {

    @Autowired
    private LdapMapper LdapMapper;
    public List<Ldap> findByadName(String adName) {
        return LdapMapper.findLdapByadName(adName);
    }

    public List<Ldap> queryPage(Integer startRows) {
        return LdapMapper.queryPage(startRows);
    }

    public int getRowCount() {
        return LdapMapper.getRowCount();
    }

    public Ldap insertLdap(Ldap ldap) {
        LdapMapper.insertLdap(ldap);
        return ldap;
    }
    public List<Ldap> ListLdap() {
        return LdapMapper.ListLdap();
    }
    public int Update(Ldap ldap) {
        return LdapMapper.Update(ldap);
    }
    public int delete(int ldapID) {
        return LdapMapper.delete(ldapID);
    }
}
