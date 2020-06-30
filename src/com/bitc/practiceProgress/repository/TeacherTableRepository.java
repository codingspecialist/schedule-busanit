package com.bitc.practiceProgress.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitc.practiceProgress.db.DBConn;
import com.bitc.practiceProgress.dto.MonthTime;
import com.bitc.practiceProgress.dto.TeacherMonthStatusDto;
import com.bitc.practiceProgress.model.ClassTable;
import com.bitc.practiceProgress.model.TeacherTable;

public class TeacherTableRepository {
	private static final String TAG = "TeacherTableRepository : "; // TAG 생성 (오류 발견시 용이)
	private static TeacherTableRepository instance = new TeacherTableRepository();

	private TeacherTableRepository() {
	}

	public static TeacherTableRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<Integer> findMonthCount(String month) {
		List<TeacherTable> teachers = findAllSort();
		List<Integer> monthCount = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		for (int i=0; i<teachers.size(); i++) {
			if(i == teachers.size()-1) {
				sb.append("(select count(*) from practice_table where substr(class_date,6,2) = '"+month+"' and prof = '"+teachers.get(i).getTeacherName()+"') ");
			}else {
				sb.append("(select count(*) from practice_table where substr(class_date,6,2) = '"+month+"' and prof = '"+teachers.get(i).getTeacherName()+"'), ");
			}
				
		}
		sb.append("from dual");
		final String SQL = sb.toString();
		System.out.println(sb.toString());
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				for (int i=0; i<teachers.size(); i++) {
					monthCount.add(rs.getInt(i+1));
				}

			}
		
			return monthCount;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAllCount : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	
	public List<Integer> findYearCount(String year, String teacherType) {
		List<TeacherTable> teachers = findAllSort(teacherType);
		System.out.println("하하하 : "+teachers.size());
		List<Integer> yearCount = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		for (int i=0; i<teachers.size(); i++) {
			if(i == teachers.size()-1) {
				sb.append("(select count(*) from practice_table where substr(class_date, 1,4) = '"+year+"' and prof = '"+teachers.get(i).getTeacherName()+"')");
			}else {
				sb.append("(select count(*) from practice_table where substr(class_date, 1,4) = '"+year+"' and prof = '"+teachers.get(i).getTeacherName()+"'), ");
			}
				
		}
		sb.append("from dual");
		final String SQL = sb.toString();
		System.out.println(sb.toString());
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				for (int i=0; i<teachers.size(); i++) {
					yearCount.add(rs.getInt(i+1));
				}

			}
		
			return yearCount;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAllCount : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	public TeacherMonthStatusDto findTime(int classId, String month) {
		List<TeacherTable> teachers = findAllSort();
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("(select class_name from class_table where id = "+classId+"), ");
		for (int i=0; i<teachers.size(); i++) {
			if(i == teachers.size()-1) {
				sb.append("(select count(*) from practice_table where substr(class_date,6,2) = '"+month+"' and class_id = "+classId+" and prof = '"+teachers.get(i).getTeacherName()+"') as '"+teachers.get(i).getTeacherName()+"'");
			}else {
				sb.append("(select count(*) from practice_table where substr(class_date,6,2) = '"+month+"' and class_id = "+classId+" and prof = '"+teachers.get(i).getTeacherName()+"') as '"+teachers.get(i).getTeacherName()+"', ");
			}
				
		}
		sb.append("from dual");
		final String SQL = sb.toString();
		TeacherMonthStatusDto dto = new TeacherMonthStatusDto();
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				List<MonthTime> monthTimes = new ArrayList<MonthTime>();
				dto.setClassName(rs.getString(1));
				for (int i=0; i<teachers.size(); i++) {
					MonthTime monthTime = new MonthTime();
					monthTime.setTime(rs.getInt(i+2));
					monthTime.setTeacharName(teachers.get(i).getTeacherName());
					monthTimes.add(monthTime);
				}
				dto.setMonthTime(monthTimes);
			}
		
			return dto;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findTime : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	public List<TeacherTable> findAllSort(String teacherType) {
		final String SQL = "select * from teacher_table where teacher_type = ? order by teacher_part desc, teacher_type desc, teacher_name";
		
		List<TeacherTable> teacherTables = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, teacherType);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				TeacherTable teacherTable = TeacherTable.builder()
						.id(rs.getInt(1))
						.teacherName(rs.getString("teacher_name"))
						.teacherType(rs.getString("teacher_type"))
						.teacherPart(rs.getString("teacher_part"))
						.build();
				teacherTables.add(teacherTable);
			}
		
			return teacherTables;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAllSort : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	public List<TeacherTable> findAllSort() {
		final String SQL = "select * from teacher_table order by teacher_part desc, teacher_type desc, teacher_name";
		
		List<TeacherTable> teacherTables = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				TeacherTable teacherTable = TeacherTable.builder()
						.id(rs.getInt(1))
						.teacherName(rs.getString("teacher_name"))
						.teacherType(rs.getString("teacher_type"))
						.teacherPart(rs.getString("teacher_part"))
						.build();
				teacherTables.add(teacherTable);
			}
		
			return teacherTables;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAllSort : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	
	
	public List<TeacherTable> findAll() {
		final String SQL = "SELECT * FROM teacher_table ORDER BY teacher_name";
		
		List<TeacherTable> teacherTables = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				TeacherTable teacherTable = TeacherTable.builder()
						.id(rs.getInt(1))
						.teacherName(rs.getString("teacher_name"))
						.teacherType(rs.getString("teacher_type"))
						.teacherPart(rs.getString("teacher_part"))
						.build();
				teacherTables.add(teacherTable);
			}
		
			return teacherTables;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	public int delete(int id) {
		final String SQL = "DELETE FROM teacher_table WHERE id = ?";
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

	
	public int save(TeacherTable teacherTable) {
		final String SQL = "INSERT INTO teacher_table(teacher_name, teacher_type, teacher_part) "
				+ "VALUES(?, ?, ?) ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, teacherTable.getTeacherName());
			pstmt.setString(2, teacherTable.getTeacherType());
			pstmt.setString(3, teacherTable.getTeacherPart());
			
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
	
	
	
	
	
	

}
