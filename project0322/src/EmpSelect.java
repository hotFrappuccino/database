import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpSelect {

	public static void main(String[] args) {
		//EMP 테이의 데이터를 변수에 저장해서 읽기_숫자컬럼인 EMPNO, 문자열인 ENAM, 날짜형식인 HIREDATE 만 읽기
		
		//파일을 읽고 쓰기를 하거나 네트워크 작업 또는 데이터베이스 연동 시에는 반드시 예외처리를 해야하고 close를 해야 함.
		try( Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user11","user11");
			PreparedStatement pstmt = con.prepareStatement("Select EMPNO, ENAME, HIREDATE from EMP");){
			//SQL 문장에 ?가 있었다면 실제 데이터로 치환 : Data Binding
			// SQL을 실행
			ResultSet rs = pstmt.executeQuery();
			// 결과 사용
			while(rs.next()) {
				//전부 문자열로 읽는 것 가능
				String empno = rs.getString("empno");
				String ename = rs.getString("ename");
//				 hiredate = rs.getString("hiredate");
		/*hiredate를 날짜로 받기
		 	java.util.Date - 날짜와 시간 모두 저장 
		 	java.sql.Date  - 날짜만 저장 
		 */
				java.util.Date hiredate = rs.getDate("hiredate");
		
				
				//데이터 출력
				System.out.printf("사번 : %s 이름 : %s 입사일 : %s\n", empno, ename, hiredate);
				
			}
			
		}catch(Exception e) {
			System.out.printf("읽기 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}

	}

}
