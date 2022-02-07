package com.example.spring03;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MemoDao {
	@Select("select * from memo order by idx desc")
	List<MemoDto> list();
	//완성된 메소드는 아니지만 sql문이 들어갈수 있음..
	
	@Insert("insert into memo (idx, writer, memo) values "
			+ "((select nvl(max(idx)+1, 1) from memo),#{writer},#{memo})")
	void insert(@Param("writer") String writer, @Param("memo") String memo);
	
	@Select("select * from memo where idx=#{idx}")
	MemoDto view(@Param("idx") int idx);
	
	@Update("update memo set writer=#{writer}, memo=#{memo} where idx=#{idx}")
	void update(MemoDto dto);
	
	@Delete("delete from memo where idx=#{idx}")
	void delete(@Param("idx") int idx);
}
