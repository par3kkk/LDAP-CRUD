package com.ply.entity;

public class  Ldap {
    // 定义类
    private int ldapID;
    private String adName; // 域名
    private String ip; // IP地址
    private String searchPath; // 用户搜索路径
    private String bindUserPath; // 代理用户路径
    private String bindUserPassword; // 代理用户密码

    public Ldap ldap;
    // get方法
    public Ldap(int ldapID){
        this.ldapID = ldapID;
    };

    public int getLdapID() {
        return ldapID;
    }

    public String getadName() {
        return adName;
    };

    public String getip() {
        return ip;
    };

    public String getSearchPath() {
        return searchPath;
    };

    public String getBindUserPath() {
        return bindUserPath;
    };

    public String getBindUserPassword() {
        return bindUserPassword;
    };

    // set方法

    public void setadName(String adName) {
        this.adName = adName;
    };

    public void setip(String ip) {
        this.ip = ip;
    };

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    };

    public void setBindUserPath(String bindUserPath) {
        this.bindUserPath = bindUserPath;
    };

    public void setBindUserPassword(String bindUserPassword) {
        this.bindUserPassword = bindUserPassword;
    };

    public int setLdapID(int ldapID) {
        return ldapID;
    }

    @Override
    public String toString() {
        return "ldap{" +
                "adName=" +adName +
                ", ip=" + ip +
                ", searchPath=" +searchPath + '\'' +
                ", bindUserPath=" + bindUserPath + '\'' +
                ", bindUserPassword=" +bindUserPassword +'}';
    }


}

