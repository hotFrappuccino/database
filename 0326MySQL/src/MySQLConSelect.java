import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MySQLConSelect {

	public static void main(String[] args) {
		// 1. 드라이버 클래스를 로드 - 처음에 한 번만 수행
		// 이 부분에서 오류가 발생하면 드라이버클래스 이름 오류이거나(이건 찾아서 기재) 드라이버 파일을 프로젝트의 build path에 추가하지
		// 않은 것.
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.printf("드라이버 클래스 로드: %s\n", e.getMessage());
		}
		// 2. 데이터베이스 연결
		// try(){} : try with resource 구문으로 AutoClosable 인터페이스를 implements한 클래스의 인스턴스를
		// 생성하는 구문을 삽입할 수 있는데
		// 이 경우에는 close()를 호출하지 않아도 영역을 벗어나면 자동으로 호출됨.
/*
		  try(Connection con = DriverManager.getConnection(
		  "jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8",
		  "root","1111"); //sql 실행객체 - 입력받아서 넣을 자리는 ?로 설정 PreparedStatement pstmt =
		  con.prepareStatement( "select avg(price) from buytbl");) { //저장할 변수를 생성
		  //avg이기 때문에 소수부분이 있는 수가 나오므로 double. double avg = -1; ResultSet rs =
		  pstmt.executeQuery(); if(rs.next()) { //avg=rs.getDouble(1) avg =
		  rs.getDouble("avg(price)");
		  
		  } System.out.printf("평균 : %f\n", avg);
		  
		  rs.close();
		  
		  } catch(Exception e) { System.out.printf("데이터베이스 사용 예외:%s\n",
		  e.getMessage()); }
 */
/*
		  try(Connection con = DriverManager.getConnection(
		  "jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8",
		  "root","1111"); //sql 실행객체 - 입력받아서 넣을 자리는 ?로 설정 PreparedStatement pstmt =
		  con.prepareStatement( "select userid from buytbl group by userid");) { //저장할
		  변수를 생성 //이건 userid가 중복없이 쭉 나올거. 그러므로 세로. List List<String> list = new
		  ArrayList(); ResultSet rs = pstmt.executeQuery(); while(rs.next()) {
		  list.add(rs.getString("userid"));
		  
		  } for(String temp : list) { System.out.printf("userid : %s\n",temp); }
		  rs.close();
		  
		  } catch(Exception e) { System.out.printf("데이터베이스 사용 예외:%s\n",
		  e.getMessage()); }
*/
		
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8", "root", "1111");
				// sql 실행객체 - 입력받아서 넣을 자리는 ?로 설정
				PreparedStatement pstmt = con.prepareStatement("select userid, sum(price * amount) buy from buytbl group by userid");) {
			//조회할 내용이 연산식인 경우는 연산식에 별명을 사용하는 것이 좋습니다.
			
			// 저장할 변수를 생성
			// 이건 userid가 하나니까 열이 1개. select한 게 두개라서 행이 2개. 그러므로 Map!!!
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//HashMap(해싱 - 키의 순서를 모름)
				//LinkedHashMap(키의 순서대로 저장)
				//TreeMap(키를 정렬하여 저장)
				Map<String,Object> map = new LinkedHashMap<String, Object>();
				map.put("userid",rs.getString("userid"));
				map.put("sum",rs.getInt("buy"));
				list.add(map);
			}
			for (Map temp : list) {
				System.out.printf("%s\n", temp);
			}
			rs.close();

		} catch (Exception e) {
			System.out.printf("데이터베이스 사용 예외:%s\n", e.getMessage());
		}
	}

}
