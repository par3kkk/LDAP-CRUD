package com.ply.mapper;

import com.ply.entity.Ldap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LdapMapper {
    public List<Ldap> findLdapByadName(String adName);
    public List<Ldap> ListLdap();
    public List<Ldap> queryPage(Integer startRows);
    public int getRowCount();
    public int insertLdap(Ldap ldap);
    public int delete(int ldapID);
    public int Update(Ldap ldap);
}
