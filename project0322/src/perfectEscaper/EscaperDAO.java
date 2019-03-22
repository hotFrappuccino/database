package perfectEscaper;

import java.util.List;

public interface EscaperDAO {
	//데이터를 삽입하는 메소드
	public int insertEscaper(EscaperVO vo);
	
	//데이터를 수정하는 메소드
	public int updateEscaper(EscaperVO vo);
		
	//데이터를 삭제하는 메소드
	public int deleteEscaper(int num);
	
	//데이터 전체를 가져오는 메소드
	public List<EscaperVO> getAllDataList();
}
