package perfectEscaper;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		// DAO 클래스의 인스턴스를 생성
		EscaperDAO dao = EscaperDAOImpl.getInstance();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.printf("메뉴입력(1.삽입 2.전체 가져오기 100:종료) : ");
			String menu = sc.nextLine();
			
			//이름과 내용 그리고 날짜를 저장할 임시변수를 선언
			String name = null;
			String content = null;
			Date esdate = null;
			
			switch (menu) {
			case "1":
				System.out.printf("이름을 입력하세요:");
				name = sc.nextLine();
				System.out.printf("내용을 입력하세요 :");
				content = sc.nextLine();
				Calendar cal = new GregorianCalendar();
				esdate = new Date(cal.getTimeInMillis());
				
				EscaperVO vo = new EscaperVO();
				vo.setName(name);
				vo.setContent(content);
				vo.setEsdate(esdate);
				
				int result = dao.insertEscaper(vo);
				if(result>0) {
					System.out.printf("삽입에 성공했습니다.\n");
				}else {
					System.out.printf("삽입 실패.\n");
				}
				
				System.out.printf("엔터를 누르면 넘어갑니다.");
				sc.nextLine();
				break;
		
			case "2":
				List<EscaperVO> list1 = dao.getAllDataList();
				for(EscaperVO temp : list1) {
					System.out.printf("%s\n", temp);
				}
				System.out.printf("엔터를 누르면 넘어갑니다.");
				sc.nextLine();
				break;
				
			case "100":
				System.out.printf("프로그램을 종료합니다.\n");
				sc.close();
				System.exit(0);
			}
		}

	}

}
