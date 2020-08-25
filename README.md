# LDAP
LDAP(CRUD) Based on SpringBoot and Vue and Mysql

# LDAP-front-end
-Development Tool : VS CODE<br>
-Development FrameWork : Vue + axios<br>
-Package Management Tool : npm<br>
-Package Tool : webpack<br>

<p>Directory LDAP is the package of front-end;</p><br>
<strong>USE npm -install</strong><br>
<strong>USE npm -install axios</strong><br>
<strong>Use npm -install element-ui -S</strong><br>

# LDAP-back-end
-Development Tool : IntelliJ IDEA<br>
-Development Framework : Springboot + Mybatis<br>
-Package Tool : Maven<br>
-SQL : MYSQL<br>

<p>Directory LDAP-BACK is the package of back-end</p><br>

# MySQL Settings
Create Table: CREATE TABLE `ldap` (<br>
  `ldapID` int NOT NULL AUTO_INCREMENT,<br>
  `adName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,<br>
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,<br>
  `searchPath` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,<br>
  `bindUserPath` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,<br>
  `bindUserPassword` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,<br>
  PRIMARY KEY (`ldapID`) USING BTREE<br>
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT<br>


