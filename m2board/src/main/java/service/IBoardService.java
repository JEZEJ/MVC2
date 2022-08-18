package service;

import java.util.Map;

public interface IBoardService {
	
	// 반환값 = List<Board>, int lastPage 
	// 반환값이 두개니깐 Map써주기
	Map<String,Object> getBoardList(int rowPerPage,int currentPage);
	
	
}
