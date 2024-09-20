package com.sist.mapper;

public class RecipeMapper {

	
	
	
	@Update("UPDATE recipe SET "
			+ "hit = hit+1;"
			+ "WHERE no=#{no}"
			+ "")
}
