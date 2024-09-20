package com.sist.dao;

public class MusicDAO {

	
	public List<MusicVO> musicTitleFindData(String title)
	{
		return mapper.musicTitleFindData(title);
	}
	
	
	
	
	// 통합 검색 => 동적 쿼리
	/*
	 * 	<foreach> : IN
	 * 	<trim> : 제거 / 추가 => OR / AND
	 * 	<if> : 조건문
	 * 	<choose> => 다중 조건문
	 * 	  <when>
	 * 	</choose>
	 * ================================
	 * 	=> <resultMap> : 조인
	 *   
	 */
}
