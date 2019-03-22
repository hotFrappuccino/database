import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ProcedureMain {

	public static void main(String[] args) {
		// 프로시저 이용하기
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
				"user11");
	// 일반적 삽입 PreparedStatement pstmt = con.prepareStatement("insert into
				// sample(num,message,writedate values(?,?,?)");
	// 프로시저 삽입
				CallableStatement cstmt = con.prepareCall("{call insertsample(?,?,?)}");) {
			// ?에 값을 바인딩
			cstmt.setInt(1, 3);
			cstmt.setString(2, "오늘 저녁에 뭐해?");

			// java.sql.Date는 매개변수 없는 생성자가 없어서 calendar 객체를 만들고 date로 변환
			Calendar cal = new GregorianCalendar();
			java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
			cstmt.setDate(3, today);

			//cstmt 실행
			int r = cstmt.executeUpdate();
			if(r>0) {
				System.out.printf("삽입 성공");
			}
		} catch (Exception e) {
			System.out.printf("읽기 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}

	}

}
