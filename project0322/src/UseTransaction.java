import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class UseTransaction {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11","user11");
				PreparedStatement pstmt = con.prepareStatement("insert into sample(num, message) values(?,?)");
			){
			
		//commit이나 rollback을 직접 수행할 수 있도록 설정
			con.setAutoCommit(false);//오토커밋을 false로
			pstmt.setInt(1, 12);
			pstmt.setString(2, "야호");
			
			pstmt.executeUpdate();
			System.out.printf("삽입성공");
			con.commit();//현재까지의 작업내용을 바로 반영
			Thread.sleep(30000); // 이 위의 commit이 없다면 이 시간이 지나고 메소드가 완전히 종료되었을 때 꺼지면서 커밋. 이 시간동안은 커밋되지 않고 기다려.
//			con.rollback();
		} catch (Exception e) {
			System.out.printf("읽기 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
	}

}
