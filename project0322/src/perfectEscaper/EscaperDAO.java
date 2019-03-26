package perfectEscaper;

import java.util.List;
import java.util.Map;

public interface EscaperDAO {
	//데이터를 삽입하는 메소드
	public int insertEscaper(EscaperVO vo);

	//데이터 전체를 가져오는 메소드
	public List<EscaperVO> getAllDataList();
	
	//다섯 개 데이터를 가져오는 메소드
	public List<EscaperVO> getFiveDataList();
	
	//더보기
	public List<EscaperVO> getMoreDataList();
	
	//페이지 처리를 위한 메소드
	//리턴타입은 이전과 동일하게 List
	//매개변수로 페이지번호와 조회할 데이터 개수를 매개변수로 받습니다.
	//모바일에서는 데이터개수를 정해놓고 가져오지만 pc용 웹을 구현할 때는 데이터 개수를 선택하도록 합니다.
	//특별한 경우가 아니면 매개변수는 1개로 만드는 것이 좋습니다.
	//map은 출력할 페이지 번호와 데이터개수를 갖는 map
	public List<EscaperVO> pageList(Map<String,Object> map);
	
	//상세보기 - 기본키를 가지고 데이터 1개를 찾아오는 메소드
	public EscaperVO getDetail(int num);
	
	//데이터를 수정하는 메소드
	public int updateEscaper(EscaperVO vo);
		
	//데이터를 삭제하는 메소드
	public int deleteEscaper(int num);
}
