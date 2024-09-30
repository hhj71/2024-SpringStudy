package com.sist.vo;

import lombok.Data;

/*
 * USERID	VARCHAR2(20 BYTE)
AUTHORITY	VARCHAR2(20 BYTE)
 */
@Data
public class AuthorityVO {
	private String userId, authority;
}
