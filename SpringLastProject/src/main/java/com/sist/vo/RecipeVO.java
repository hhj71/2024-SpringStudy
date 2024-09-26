package com.sist.vo;

import lombok.Data;

/*
 * NO	NUMBER
TITLE	VARCHAR2(1000 BYTE)
POSTER	VARCHAR2(500 BYTE)
CHEF	VARCHAR2(100 BYTE)
LINK	VARCHAR2(500 BYTE)
HIT	NUMBER
 */
@Data
public class RecipeVO {
	private int no, hit;
	private String title, poster, chef, content;
}
