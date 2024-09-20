package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

public class MusicMapper {
	@Select("SELECT mno, title, singer, album, idcrement, state "
			+"FROM genie_music ORDER BY mno ASC")
	
	
	
	
	
	public List<MusicVO> musicSingerFindData(String singer);
	
}
