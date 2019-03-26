import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MySQLConnect 
{
	
	public static void main(String[] args) 
	{
		//1. 드라이버 클래스를 로드 - 처음에 한 번만 수행
		//이 부분에서 오류가 발생하면 드라이버클래스 이름 오류이거나(이건 찾아서 기재) 드라이버 파일을 프로젝트의 build path에 추가하지 않은 것.
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) 
		{
			System.out.printf("드라이버 클래스 로드: %s\n", e.getMessage());
		}
		//2. 데이터베이스 연결
		//try(){} : try with resource 구문으로 AutoClosable 인터페이스를 implements한 클래스의 인스턴스를 생성하는 구문을 삽입할 수 있는데
		//이 경우에는 close()를 호출하지 않아도 영역을 벗어나면 자동으로 호출됨.
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				//sql 실행객체 - 입력받아서 넣을 자리는 ?로 설정
				PreparedStatement pstmt = con.prepareStatement(
						"update usertbl set addr=?, mobile=? where userid = ?");)
		{
//			System.out.printf("%s\n", con);
			//SQL 실행 객체의 ?에 실제 데이터대입 - 바인딩
			pstmt.setString(1, "서울");
			pstmt.setString(2, "01022446688");
			pstmt.setString(3, "moon");
			
			//SQL 실행
			//select 는 ResultSet(List나 일반객체 또는 스칼라 데이터)로 리턴
			//나머지 SQL은 전부 정수로 리턴 _ 정수의 값을 가지고 성공여부 판단
			int result = pstmt.executeUpdate();
			//이 정수 값은 영향받은 행의 개수 - 삽입은 0보다 크면 성공
			//수정 : 0보다 크면 수정할 데이터가 있어서 수정을 한 것이고 0이 리턴되면 수정할 데이터가 없어서 수정을 안한 것.
			if(result>0)
			{
				System.out.printf("데이터 수정 성공 \n");
			}
			else
			{
				System.out.printf("조건에 맞는 데이터가 없습니다. \n");//result가 0인 경우 실패한 게 아니라 수정할 데이터가 없었던 것.
			}
			
			
		}
		catch(Exception e) 
		{
			System.out.printf("데이터베이스 사용 예외:%s\n", e.getMessage());
		}
		
		
	}
	
}
