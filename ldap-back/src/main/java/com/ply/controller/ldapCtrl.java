package com.ply.controller;

import com.ply.entity.Ldap;
import com.ply.service.ldapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ldapCtrl {
    @Autowired
    private ldapService ldapService;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Integer delete(Integer ldapID) {
        System.out.println(ldapID);
        int result = ldapService.delete(ldapID);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Ldap ldap) {
        int result = ldapService.Update(ldap);
        if(result >=1) {
            return "修改成功";
        }   else {
            return "修改失败";
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Ldap insert(Ldap ldap){
        return ldapService.insertLdap(ldap);
    }

    @RequestMapping("/ListLdap")
    @ResponseBody
    public List<Ldap> ListLdap(){
        return ldapService.ListLdap();
    }

    @RequestMapping("ListByadName")
    @ResponseBody
    public List<Ldap> ListLdapByadName(String adName) {
        return ldapService.findByadName(adName);
    }

    @RequestMapping(value="/page")
    @ResponseBody
    public List<Ldap> page(Integer page){
        int pageNow = page == null ? 1 : page;
        int pageSize = 5;
        int startRows = pageSize*(pageNow-1);
        List<Ldap> list = ldapService.queryPage(startRows);
        return list;
    }


    @RequestMapping(value="/rows")
    @ResponseBody
    public int rows(){

        return ldapService.getRowCount();
    }
}
