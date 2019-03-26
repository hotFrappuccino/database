package perfectEscaper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EscaperDAOImpl implements EscaperDAO {
	// 생성자는 private으로
	private EscaperDAOImpl() {
	}

	// 자신의 타입으로 static변수를 선언
	private static EscaperDAO messageDAO;

	// 인스턴스를 만들어서 리턴하는 static 메소드를 생성
	public static EscaperDAO getInstance() {
		if (messageDAO == null) {
			messageDAO = new EscaperDAOImpl();
		}
		return messageDAO;
	}

	@Override
	public int insertEscaper(EscaperVO vo) {
		int result = -1;

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
				"user11");
				PreparedStatement pstmt = con.prepareStatement(
						"insert into escaper(num, name, content, esdate) values(escaperseq.nextval,?,?,?)");) {
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setDate(3, vo.getEsdate());

			// sql 실행
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.printf("삽입실패 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<EscaperVO> getAllDataList() {
		List list = new ArrayList();
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
				"user11");
				// 여러개의 데이터를 가져올 때는 order by가 필수 _ 꼭 쓰기.
				PreparedStatement pstmt = con.prepareStatement("select * from escaper order by esdate desc");) {

			// select 구문을 수행하고 그 결과를 rs에 저장
			ResultSet rs = pstmt.executeQuery();
			// rs를 한줄씩 읽어오기.
			while (rs.next()) {
				EscaperVO vo = new EscaperVO();
				// 읽은 데이터를 vo에 저장
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setEsdate(rs.getDate("esdate"));
				// 리스트에 추가
				list.add(vo);
			}

			rs.close();
		} catch (Exception e) {
			System.out.printf("전체가져오기 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EscaperVO> getFiveDataList() {
		List list = new ArrayList();
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
				"user11");
				// 여러개의 데이터를 가져올 때는 order by가 필수 _ 꼭 쓰기.
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT * FROM (SELECT rownum rnum, num, name, content, esdate FROM "
						+ "(SELECT * FROM ESCAPER ORDER BY num desc)) WHERE rnum <=5");) {

			// select 구문을 수행하고 그 결과를 rs에 저장
			ResultSet rs = pstmt.executeQuery();
			// rs를 한줄씩 읽어오기.
			while (rs.next()) {
				EscaperVO vo = new EscaperVO();
				// 읽은 데이터를 vo에 저장
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setEsdate(rs.getDate("esdate"));
				// 리스트에 추가
				list.add(vo);
			}

			rs1.close();
		} catch (Exception e) {
			System.out.printf("5개만 가져오기 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EscaperVO> getMoreDataList() {
		List list = new ArrayList();
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
				"user11");
				// 여러개의 데이터를 가져올 때는 order by가 필수 _ 꼭 쓰기.
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT * FROM (SELECT rownum rnum, num, name, content, esdate FROM"
						+ " (SELECT * FROM ESCAPER ORDER BY num desc)) WHERE rnum >5");) {

			// select 구문을 수행하고 그 결과를 rs에 저장
			ResultSet rs = pstmt.executeQuery();
			// rs를 한줄씩 읽어오기.
			while (rs.next()) {
				EscaperVO vo = new EscaperVO();
				// 읽은 데이터를 vo에 저장
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setEsdate(rs.getDate("esdate"));
				// 리스트에 추가
				list.add(vo);
			}

			rs.close();
		} catch (Exception e) {
			System.out.printf("더보기 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EscaperVO> pageList(Map<String, Object> map) {
		List list = new ArrayList();
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
				"user11");
				// 여러개의 데이터를 가져올 때는 order by가 필수 _ 꼭 쓰기.
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT * FROM (SELECT rownum rnum, num, name, content, esdate FROM "
						+ "(SELECT * FROM ESCAPER ORDER BY num desc)) WHERE rnum >=? and rnum <=?");) {
			// map에서 가져온 이름은 앞에서 만들어주어야 함. 이름이 틀리면 NullPointerException이 발생.
			int page = (Integer) map.get("page");
			int cnt = (Integer) map.get("cnt");

			// 페이지 번호와 페이지당 데이터 개수를 이용해서 가져올 데이터의 시작번호와 끝번호를 생
			pstmt.setInt(1, cnt * (page - 1) + 1);
			pstmt.setInt(2, cnt * page);

			ResultSet rs = pstmt.executeQuery();
			// 읽은 데이터가 없으면 while문이 한번도 수행되지 않아서 list의 size가 0이 됨.
			while (rs.next()) {
				EscaperVO vo = new EscaperVO();
				// 읽은 데이터를 vo에 저장
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setEsdate(rs.getDate("esdate"));
				// 리스트에 추가
				list.add(vo);
			}

			rs.close();

		} catch (Exception e) {
			System.out.printf("페이지 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public EscaperVO getDetail(int num) {
		// num에 해당 데이터가 없을 때에는 null을 리턴하도록 함. _ list는 size가 0인 것 기준, 데이터 하나는 null이냐 아니냐.
		EscaperVO vo = null;
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
				"user11"); PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ESCAPER WHERE num=?");) {
			pstmt.setInt(1, num);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new EscaperVO();
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setEsdate(rs.getDate("esdate"));
			}

			rs.close();

		} catch (Exception e) {
			System.out.printf("상세보기 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}

		return vo;
	}

	@Override
	public int updateEscaper(EscaperVO vo) {
		int result = -1;
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
				"user11");
				PreparedStatement pstmt = con.prepareStatement("update escaper set name =?,content=?,esdate = sysdate"
						+ " where num=?");) {
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());

			// sql 실행
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.printf("수정실패 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public int deleteEscaper(int num) {
		int result = -1;
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user11",
				"user11"); PreparedStatement pstmt = con.prepareStatement("delete from escaper where num = ?");) {
			pstmt.setInt(1, num);

			// sql 실행
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.printf("삭제실패 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}