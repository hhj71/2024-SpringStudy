package com.sist.dao;

public class MusicDAO {

	
	public List<MusicVO> musicTitleFindData(String title)
	{
		return mapper.musicTitleFindData(title);
	}
	
	
	
	
	// ���� �˻� => ���� ����
	/*
	 * 	<foreach> : IN
	 * 	<trim> : ���� / �߰� => OR / AND
	 * 	<if> : ���ǹ�
	 * 	<choose> => ���� ���ǹ�
	 * 	  <when>
	 * 	</choose>
	 * ================================
	 * 	=> <resultMap> : ����
	 *   
	 */
}
