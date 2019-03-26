package perfectEscaper;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Controller {

	public static void main(String[] args) {
		// DAO 클래스의 인스턴스를 생성
		EscaperDAO dao = EscaperDAOImpl.getInstance();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.printf("메뉴입력(1.삽입 2.전체 가져오기 3.번호순으로 다섯개만 가져오기 4.더보기 5.페이지보기 6.데이터수정 7.데이터 삭제 100:종료) : ");
			String menu = sc.nextLine();

			// 이름과 내용 그리고 날짜를 저장할 임시변수를 선언
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
				if (result > 0) {
					System.out.printf("삽입에 성공했습니다.\n");
				} else {
					System.out.printf("삽입 실패.\n");
				}

				System.out.printf("엔터를 누르면 넘어갑니다.");
				sc.nextLine();
				break;

			case "2":
				List<EscaperVO> list = dao.getAllDataList();
				for (EscaperVO temp : list) {
					System.out.printf("%s\n", temp);
				}
				System.out.printf("엔터를 누르면 넘어갑니다.");
				sc.nextLine();
				break;

			case "3":
				List<EscaperVO> list1 = dao.getFiveDataList();
				for (EscaperVO temp : list1) {
					System.out.printf("%s\n", temp);
				}
				System.out.printf("엔터를 누르면 넘어갑니다.");
				sc.nextLine();
				break;

			case "4":
				List<EscaperVO> list2 = dao.getMoreDataList();
				for (EscaperVO temp : list2) {
					System.out.printf("%s\n", temp);
				}
				System.out.printf("엔터를 누르면 넘어갑니다.");
				sc.nextLine();
				break;

			case "5":
				// 페이지 번호와 데이터 개수 입력받
				System.out.printf("페이지를 입력하세요");
				int page = sc.nextInt();
				System.out.printf("보고싶은 개수");
				int cnt = sc.nextInt();

				// 파라미터 만들기
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("page", page);
				map.put("cnt", cnt);

				List<EscaperVO> list11 = dao.pageList(map);
				if (list11.size() == 0) {
					System.out.printf("읽어올 데이터가 없습니다.\n");
				} else {

					for (EscaperVO temp : list11) {
						System.out.printf("%s\n", temp);
					}
				}
				System.out.printf("엔터를 누르면 넘어갑니다.");
				sc.nextLine();
				break;
		
			case "6":
				System.out.printf("수정할 글번호 입력:");
				int updatenum = sc.nextInt();
				EscaperVO vo2 = dao.getDetail(updatenum);
				sc.nextLine();
				//데이터가 없는 경우
				if(vo2==null) {
					System.out.printf("없는 번호입니다.\n");
				}else {
					//데이터가 존재하는 경우
					System.out.printf("이름 수정 :");
					String nm = sc.nextLine();
					System.out.printf("내용 수정 :");
					String cont = sc.nextLine();
					
					vo2.setName(nm);
					vo2.setContent(cont);
					int r = dao.insertEscaper(vo2);
					if(r>=0) {
						JOptionPane.showMessageDialog(null, "수정 성공");
					}else {
						JOptionPane.showMessageDialog(null, "수정 실패");
					}
				}
				break;
				
			case "7":
				System.out.printf("삭제할 글번호 입력:");
				int deletenum = sc.nextInt();
				EscaperVO vo3 = dao.getDetail(deletenum);	//데이터가 존재하는지 확인
				System.out.printf("%s\n", vo3);
				sc.nextLine();
				//데이터가 없는 경우
				if(vo3==null) {
					System.out.printf("없는 번호입니다.\n");
				}else {
					//데이터가 존재하는 경우
					System.out.printf("정말로 삭제하시겠습니까? (삭제-1 취소-2");
					int m = sc.nextInt();
					if(m==1) {
						int r = dao.deleteEscaper(deletenum);
						if(r>=0) {
							JOptionPane.showMessageDialog(null, "삭제 성공");
						}else {
							JOptionPane.showMessageDialog(null, "삭제 실패");
						}
					}	
				}
				break;
				
			case "100":
				System.out.printf("프로그램을 종료합니다.\n");
				sc.close();
				System.exit(0);
			}
		}

	}

}
