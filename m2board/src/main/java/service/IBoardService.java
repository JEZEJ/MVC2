package service;

import java.util.List;
import java.util.Map;

import vo.Board;
import vo.Member;

public interface IBoardService {
	
	// 반환값 = List<Board>, int lastPage 
	// 반환값이 두개니깐 Map써주기
	// 게시글 목록
	Map<String,Object> getBoardList(int rowPerPage,int currentPage);
	
	// 게시글 상세보기
	List<Board> getBoardOne(int boardNo); //vo안에 Board에 있는 값이랑 파라미터랑 똑같이줘야함
	
	
	
	
	
	
}
