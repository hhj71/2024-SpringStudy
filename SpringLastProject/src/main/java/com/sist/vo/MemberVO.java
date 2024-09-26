package com.sist.vo;

import java.util.Date;

import lombok.Data;

/*
 * USERID	VARCHAR2(20 BYTE)
USERNAME	VARCHAR2(50 BYTE)
USERPWD	VARCHAR2(20 BYTE)
ENABLED	NUMBER(1,0)
SEX	VARCHAR2(6 BYTE)
BIRTHDAY	VARCHAR2(20 BYTE)
EMAIL	VARCHAR2(100 BYTE)
POST	VARCHAR2(10 BYTE)
ADDR1	VARCHAR2(500 BYTE)
ADDR2	VARCHAR2(500 BYTE)
PHONE	VARCHAR2(20 BYTE)
CONTENT	CLOB
REGDATE	DATE
MODIFYDATE	DATE
LASTLOGIN	DATE
 */
@Data
public class MemberVO {
	private int enabled;
	private String userId, userName, userPwd, sex, post, email, addr1, addr2, phone, content, bday;
	private Date regdate, modifydate, lastlogin;
	private String msg, authority;
}
