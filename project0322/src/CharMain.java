import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CharMain {

		public static void main(String[] args) {
		
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
					"user11");
					PreparedStatement pstmt = con.prepareStatement("SELECT * FROM sample WHERE lower(message) = 'hi'");
					) {
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					System.out.printf("번호 : %d 메시지 : %s, 날짜 : %s\n",
							rs.getInt("num"),
							rs.getString("message").trim(),
							rs.getString("writedate"));
				}
			} catch (Exception e) {
				System.out.printf("읽기 예외 : %s\n", e.getMessage());
				e.printStackTrace();
			}
			//메시지 프린트된 내용 확인학.char로 써놨기 때문에 빈칸bbb

	}

}
