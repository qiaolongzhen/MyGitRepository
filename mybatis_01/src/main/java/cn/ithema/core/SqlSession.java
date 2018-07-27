package cn.ithema.core;

import java.util.List;

public interface SqlSession {
	
	<E> List<E> selectAll(String mapperId) throws Exception;

}
