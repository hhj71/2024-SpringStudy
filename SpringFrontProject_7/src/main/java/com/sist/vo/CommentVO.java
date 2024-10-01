package com.sist.vo;

import java.util.*;
/*
 * CNO	NUMBER
RNO	NUMBER
ID	VARCHAR2(20 BYTE)
NAME	VARCHAR2(50 BYTE)
SEX	VARCHAR2(50 BYTE)
MSG	CLOB
REGDATE	DATE
LIKECOUNT	NUMBER
GROUP_ID	NUMBER
GROUP_STEP	NUMBER
GROUP_TAB	NUMBER
DEPTH	NUMBER
ROOT	NUMBER
MODFIYDATE	DATE
 */
// type => recipe(1) / seoul(2)/ goods(3) ===> 프로시저

import lombok.Data;

@Data
public class CommentVO {
	private int cno, rno, likecount, group_id, group_step, group_tab, depth, root, type;
	private String id, name, sex, msg, dbday;
	private Date regdate, modifydate;
}
