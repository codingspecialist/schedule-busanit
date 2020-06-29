package com.bitc.practiceProgress.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitc.practiceProgress.db.DBConn;
import com.bitc.practiceProgress.dto.ProgressInputDto;
import com.bitc.practiceProgress.dto.PracticeProgressDto;
import com.bitc.practiceProgress.model.ClassTable;

public class ClassTableRepository {
	private static final String TAG = "ClassTableRepository : "; // TAG 생성 (오류 발견시 용이)
	private static ClassTableRepository instance = new ClassTableRepository();

	private ClassTableRepository() {
	}

	public static ClassTableRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int delete(int id) {
		final String SQL = "DELETE FROM class_table WHERE id = ?";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "delete : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	
	
	
	
	public int update(ClassTable classTable) {
		final String SQL = "UPDATE class_table "
				+ "SET class_name = ?, class_part = ?, class_open = ?, class_close = ?, homeroom_prof = ?, excel_name = ?, status = ? "
				+ "WHERE id = ?";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, classTable.getClassName());
			pstmt.setString(2, classTable.getClassPart());
			pstmt.setString(3, classTable.getClassOpen());
			pstmt.setString(4, classTable.getClassClose());
			pstmt.setString(5, classTable.getHomeroomProf());
			pstmt.setString(6, classTable.getExcelName());
			pstmt.setString(7, classTable.getStatus());
			pstmt.setInt(8, classTable.getId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	public int updateExcelName(String fileName, int classId) {
		final String SQL = "UPDATE class_table SET excel_name = ? "
				+ "WHERE id = ?";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fileName);
			pstmt.setInt(2, classId);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "updateExcelName : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	
	public int updateStatusTrue(int id) {
		final String SQL = "UPDATE class_table SET status = 'true' WHERE id = ? ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "updateStatusTrue : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	public int updateStatusFalse(int id) {
		final String SQL = "UPDATE class_table SET status = 'false' WHERE id = ? ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "updateStatusFalse : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	
	public int save(ClassTable classTable) {
		final String SQL = "INSERT INTO class_table(room, class_name, class_part, class_open, class_close, homeroom_prof, status) "
				+ "VALUES(?, ?, ?, ?, ?, ?, 'true') ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, classTable.getRoom());
			pstmt.setString(2, classTable.getClassName());
			pstmt.setString(3, classTable.getClassPart());
			pstmt.setString(4, classTable.getClassOpen());
			pstmt.setString(5, classTable.getClassClose());
			pstmt.setString(6, classTable.getHomeroomProf());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	public ClassTable findById(int id) {
		final String SQL = "SELECT id, room, class_name, class_part, class_open, class_close, homeroom_prof, excel_name, status "
				+ "FROM class_table WHERE id = ? ";
		
		ClassTable classTable = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			
			classTable = new ClassTable();
			
			if (rs.next()) {
				classTable = ClassTable.builder()
						.id(rs.getInt(1))
						.room(rs.getInt(2))
						.className(rs.getString(3))
						.classPart(rs.getString(4))
						.classOpen(rs.getString(5))
						.classClose(rs.getString(6))
						.homeroomProf(rs.getString(7))
						.excelName(rs.getString(8))
						.status(rs.getString(9))
						.build();
			}
		
			return classTable;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	
	public ClassTable findByRoom(int room) {
		final String SQL = "SELECT id, room, class_name, class_part, class_open, class_close, homeroom_prof, excel_name, status "
				+ "FROM class_table WHERE room = ? and status = 'true' ";
		
		ClassTable classTable = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, room);

			rs = pstmt.executeQuery();
			
			classTable = new ClassTable();
			
			if (rs.next()) {
				classTable = ClassTable.builder()
						.id(rs.getInt(1))
						.room(rs.getInt(2))
						.className(rs.getString(3))
						.classPart(rs.getString(4))
						.classOpen(rs.getString(5))
						.classClose(rs.getString(6))
						.homeroomProf(rs.getString(7))
						.excelName(rs.getString(8))
						.status(rs.getString(9))
						.build();
			}
		
			return classTable;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findByRoom : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	public List<Integer> findIdList() {
		final String SQL = "SELECT id FROM class_table WHERE status = 'true' ";
		
		List<Integer> idList = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			idList = new ArrayList<>();

			while (rs.next()) {
				
				idList.add(rs.getInt(1));
				
			}
		
			return idList;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findIdList : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	public List<Integer> findTrueRoomList() {
		final String SQL = "SELECT room FROM class_table WHERE status = 'true' ";
		
		List<Integer> roomList = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			roomList = new ArrayList<>();

			while (rs.next()) {
				
				roomList.add(rs.getInt(1));
				
			}
		
			return roomList;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findIdList : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
	
	public List<ClassTable> findTrueClass() {
		final String SQL = "SELECT id, room, class_name, class_part, class_open, class_close, homeroom_prof, excel_name, status "
				+ "FROM class_table WHERE status = 'true' ORDER BY room ";
		
		List<ClassTable> classTables = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			classTables = new ArrayList<>();

			while (rs.next()) {
				ClassTable classTable = ClassTable.builder()
						.id(rs.getInt(1))
						.room(rs.getInt(2))
						.className(rs.getString(3))
						.classPart(rs.getString(4))
						.classOpen(rs.getString(5))
						.classClose(rs.getString(6))
						.homeroomProf(rs.getString(7))
						.excelName(rs.getString(8))
						.status(rs.getString(9))
						.build();
				
				classTables.add(classTable);
			}
		
			return classTables;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findTrueClass : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	public List<ClassTable> findFalseClass() {
		final String SQL = "SELECT id, room, class_name, class_part, class_open, class_close, homeroom_prof, excel_name, status "
				+ "FROM class_table WHERE status = 'false' and class_close > date_sub(curdate(), INTERVAL 1 YEAR) ORDER BY class_open DESC ";
		
		List<ClassTable> classTables = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			classTables = new ArrayList<>();

			while (rs.next()) {
				ClassTable classTable = ClassTable.builder()
						.id(rs.getInt(1))
						.room(rs.getInt(2))
						.className(rs.getString(3))
						.classPart(rs.getString(4))
						.classOpen(rs.getString(5))
						.classClose(rs.getString(6))
						.homeroomProf(rs.getString(7))
						.excelName(rs.getString(8))
						.status(rs.getString(9))
						.build();
				
				classTables.add(classTable);
				
			}
		
			return classTables;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findFalseClass : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	
	// 호실, 훈련명, 담임선생님을 불러옴
	public List<ProgressInputDto> findClassNameHomeroomProf() {
		final String SQL = "SELECT room, class_name, homeroom_prof FROM class_table WHERE status = 'true' ";
		List<ProgressInputDto> pids = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			pids = new ArrayList<>();
			
			for (int i = 0; i < 12; i++) {
				
				pids.add(new ProgressInputDto(0,"",""));
				
			}
			
			while (rs.next()) {

				if(rs.getInt(1) == 402) {
					pids.get(0).setRoom(rs.getInt(1));
					pids.get(0).setClassName(rs.getString(2));
					pids.get(0).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 403) {
					pids.get(1).setRoom(rs.getInt(1));
					pids.get(1).setClassName(rs.getString(2));
					pids.get(1).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 404) {
					pids.get(2).setRoom(rs.getInt(1));
					pids.get(2).setClassName(rs.getString(2));
					pids.get(2).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 405) {
					pids.get(3).setRoom(rs.getInt(1));
					pids.get(3).setClassName(rs.getString(2));
					pids.get(3).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 501) {
					pids.get(4).setRoom(rs.getInt(1));
					pids.get(4).setClassName(rs.getString(2));
					pids.get(4).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 502) {
					pids.get(5).setRoom(rs.getInt(1));
					pids.get(5).setClassName(rs.getString(2));
					pids.get(5).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 503) {
					pids.get(6).setRoom(rs.getInt(1));
					pids.get(6).setClassName(rs.getString(2));
					pids.get(6).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 504) {
					pids.get(7).setRoom(rs.getInt(1));
					pids.get(7).setClassName(rs.getString(2));
					pids.get(7).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 505) {
					pids.get(8).setRoom(rs.getInt(1));
					pids.get(8).setClassName(rs.getString(2));
					pids.get(8).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 506) {
					pids.get(9).setRoom(rs.getInt(1));
					pids.get(9).setClassName(rs.getString(2));
					pids.get(9).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 507) {
					pids.get(10).setRoom(rs.getInt(1));
					pids.get(10).setClassName(rs.getString(2));
					pids.get(10).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 508) {
					pids.get(11).setRoom(rs.getInt(1));
					pids.get(11).setClassName(rs.getString(2));
					pids.get(11).setHomeroomProf(rs.getString(3));
				}
				
			}
		
			return pids;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findClassNameHomeroomProf : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null; // 실패시
	}

}
