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
import com.bitc.practiceProgress.dto.ProfServiceTimeDto;
import com.bitc.practiceProgress.model.ClassTable;
import com.bitc.practiceProgress.model.PracticeTable;

public class PracticeTableRepository {
	private static final String TAG = "PracticeTableRepository : "; // TAG 생성 (오류 발견시 용이)
	private static PracticeTableRepository instance = new PracticeTableRepository();

	private PracticeTableRepository() {
	}

	public static PracticeTableRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	private int deleteNotAuto(int classId, Connection conn) {
		final String SQL = "DELETE FROM practice_table WHERE class_id = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, classId);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "deleteNotAuto : " + e.getMessage());
		}
		return -1; // 실패시
	}
	
	
	public int delete(int classId) {
		final String SQL = "DELETE FROM practice_table WHERE class_id = ?";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, classId);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "delete : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1; // 실패시
	}
	
	
	public int save(PracticeTable practiceTable,int classId) {
		final String SQL = "INSERT INTO practice_table(class_name, class_date, day_week, class_time, start_time, end_time, subject1, subject2, prof, room, class_id) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, practiceTable.getClassName());
			pstmt.setString(2, practiceTable.getClassDate());
			pstmt.setString(3, practiceTable.getDayWeek());
			pstmt.setInt(4, practiceTable.getClassTime());
			pstmt.setString(5, practiceTable.getStartTime());
			pstmt.setString(6, practiceTable.getEndTime());
			pstmt.setString(7, practiceTable.getSubject1());
			pstmt.setString(8, practiceTable.getSubject2());
			pstmt.setString(9, practiceTable.getProf());
			pstmt.setInt(10, practiceTable.getRoom());
			pstmt.setInt(11, classId);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1; // 실패시
	}
	
	
	public int deleteAndSaveList(List<PracticeTable> practiceTables,int classId) {
		
		final String SQL = "INSERT INTO practice_table(class_name, class_date, day_week, class_time, start_time, end_time, subject1, subject2, prof, room, class_id) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int finalResult = 1;
		
		
		try {
			
			int deleteResult = deleteNotAuto(classId , conn);
			
			if (deleteResult > 0) {
				
				if(practiceTables == null) {
					finalResult = 0;
				} else {
					for (PracticeTable practiceTable : practiceTables) {
						
						pstmt = conn.prepareStatement(SQL);
						pstmt.setString(1, practiceTable.getClassName());
						pstmt.setString(2, practiceTable.getClassDate());
						pstmt.setString(3, practiceTable.getDayWeek());
						pstmt.setInt(4, practiceTable.getClassTime());
						pstmt.setString(5, practiceTable.getStartTime());
						pstmt.setString(6, practiceTable.getEndTime());
						pstmt.setString(7, practiceTable.getSubject1());
						pstmt.setString(8, practiceTable.getSubject2());
						pstmt.setString(9, practiceTable.getProf());
						pstmt.setInt(10, practiceTable.getRoom());
						pstmt.setInt(11, classId);
						
						int result = pstmt.executeUpdate();
						
						if(result != 1) {
							finalResult = 0;
							break;
						}	
					}
				}
				
			} else {
				
				finalResult = -1;
				
			}
			
			if(finalResult == 1) {
				conn.commit();
				System.out.println("커밋");
			} else {
				conn.rollback();
				System.out.println("롤백");
			}
			return finalResult;
			
		} catch (Exception e) {
			System.out.println(TAG + "deleteAndSaveList : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	
	public int saveList(List<PracticeTable> practiceTables,int classId) {
		
		final String SQL = "INSERT INTO practice_table(class_name, class_date, day_week, class_time, start_time, end_time, subject1, subject2, prof, room, class_id) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int finalResult = 1;
		
		try {
			
			if(practiceTables == null) {
				finalResult = 0;
			} else {
				for (PracticeTable practiceTable : practiceTables) {
					
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, practiceTable.getClassName());
					pstmt.setString(2, practiceTable.getClassDate());
					pstmt.setString(3, practiceTable.getDayWeek());
					pstmt.setInt(4, practiceTable.getClassTime());
					pstmt.setString(5, practiceTable.getStartTime());
					pstmt.setString(6, practiceTable.getEndTime());
					pstmt.setString(7, practiceTable.getSubject1());
					pstmt.setString(8, practiceTable.getSubject2());
					pstmt.setString(9, practiceTable.getProf());
					pstmt.setInt(10, practiceTable.getRoom());
					pstmt.setInt(11, classId);
					
					int result = pstmt.executeUpdate();
					
					if(result != 1) {
						finalResult = 0;
						break;
					}	
				}
			}
			
			if(finalResult == 1) {
				conn.commit();
				System.out.println("커밋");
			} else {
				conn.rollback();
				System.out.println("롤백");
			}
			
			return finalResult;
			
		} catch (Exception e) {
			System.out.println(TAG + "saveList : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int allServiceTime(int classId, int room) {
		
		final String SQL = "SELECT count(room) "
				+ "FROM practice_table "
				+ "WHERE class_id = ? and room = ? and prof IS NOT NULL "
				+ "GROUP BY room ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, classId);
			pstmt.setInt(2, room);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "allServiceTime : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return -1;
	}
	
	public int countWrongClassDate(String classOpen, String classClose) {
		
		final String SQL = "SELECT count(id) FROM practice_table WHERE class_date < ? or class_date > ? ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, classOpen);
			pstmt.setString(2, classClose);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "allServiceTime : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return -1;
	}
	
	public List<ProfServiceTimeDto> profServiceTime(int classId, int room) {
		
		final String SQL = "SELECT prof, count(prof) "
				+ "FROM practice_table "
				+ "WHERE class_id = ? and room = ? and prof IS NOT NULL "
				+ "GROUP BY prof ";
		
		List<ProfServiceTimeDto> profServiceTimeDtos = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, classId);
			pstmt.setInt(2, room);
			
			rs = pstmt.executeQuery();
			
			profServiceTimeDtos = new ArrayList<>();
			
			while (rs.next()) {
				ProfServiceTimeDto profServiceTimeDto = ProfServiceTimeDto.builder()
						.prof(rs.getString(1))
						.serviceTime(rs.getInt(2))
						.build();
				
				profServiceTimeDtos.add(profServiceTimeDto);
			}
			
			return profServiceTimeDtos;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "profServiceTime : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	
	public List<List<PracticeProgressDto>> findPractice(String classDate, List<Integer> idList) {
		
		StringBuilder sb = new StringBuilder();
		if(idList.size()>0) {
			sb.append("and class_id in (");
			
			for (int i = 0; i < idList.size(); i++) {
				
				sb.append(idList.get(i));
				
				if(i<idList.size()-1) {
					sb.append(", ");	
				}
			}
			sb.append(") ");
		} else {
			sb.append("and class_id in (0) ");
		}
		
		System.out.println(sb.toString());
		
		final String SQL = "SELECT room, subject1, subject2, prof, class_time FROM practice_table "
				+ "WHERE prof IS NOT NULL AND class_date = ? "
				+ sb.toString();
		
		List<List<PracticeProgressDto>> ppdsList = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, classDate);
			
			rs = pstmt.executeQuery();
			
			ppdsList = new ArrayList<>();
			
			for (int i = 0; i < 8; i++) {
				
				List<PracticeProgressDto> ppds = new ArrayList<>();
				
				for (int j = 0; j < 12; j++) {
					
					ppds.add(new PracticeProgressDto("","",""));
					
				}
				
				
				ppdsList.add(ppds);
				
			}

			
			while (rs.next()) {
				
				if(rs.getInt(5) < 1) {
					break;
				}
				
				int rommNum = -1;
				
				if(rs.getInt(1) == 402) {
					rommNum = 0;
				} else if (rs.getInt(1) == 403) {
					rommNum = 1;
				} else if (rs.getInt(1) == 404) {
					rommNum = 2;
				} else if (rs.getInt(1) == 405) {
					rommNum = 3;
				} else if (rs.getInt(1) == 501) {
					rommNum = 4;
				} else if (rs.getInt(1) == 502) {
					rommNum = 5;
				} else if (rs.getInt(1) == 503) {
					rommNum = 6;
				} else if (rs.getInt(1) == 504) {
					rommNum = 7;
				} else if (rs.getInt(1) == 505) {
					rommNum = 8;
				} else if (rs.getInt(1) == 506) {
					rommNum = 9;
				} else if (rs.getInt(1) == 507) {
					rommNum = 10;
				} else if (rs.getInt(1) == 508) {
					rommNum = 11;
				}
				
				if(rommNum == -1) {
					break;
				}
				
				ppdsList.get(rs.getInt(5)-1).get(rommNum).setSubject1(rs.getString(2));
				ppdsList.get(rs.getInt(5)-1).get(rommNum).setSubject2(rs.getString(3));
				ppdsList.get(rs.getInt(5)-1).get(rommNum).setProf(rs.getString(4));
				
			}
					
		
			return ppdsList;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findPractice : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null; // 실패시
	}
	
	
	public List<PracticeTable> findPracticeTodayByClassId(String classDate, int classId) {
		
		final String SQL = "SELECT class_name, class_date, day_week, class_time, start_time, end_time, subject1, subject2, prof, room, class_id "
				+ "FROM practice_table "
				+ "WHERE class_date = ? and class_id = ? ";
		
		List<PracticeTable> pts = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, classDate);
			pstmt.setInt(2, classId);
			
			rs = pstmt.executeQuery();
			
			pts = new ArrayList<>();
			
			while (rs.next()) {
				PracticeTable practiceTable = PracticeTable.builder()
						.className(rs.getString("class_name"))
						.classDate(rs.getString("class_date"))
						.dayWeek(rs.getString("day_week"))
						.classTime(rs.getInt("class_time"))
						.startTime(rs.getString("start_time"))
						.endTime(rs.getString("end_time"))
						.subject1(rs.getString("subject1"))
						.subject2(rs.getString("subject2"))
						.prof(rs.getString("prof"))
						.room(rs.getInt("room"))
						.build();
				
				pts.add(practiceTable);
			}
		
			return pts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findPracticeTodayByClassId : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	
	public List<PracticeProgressDto> findPracticeNow(int classTime, String classDate, List<Integer> idList) {
		
		StringBuilder sb = new StringBuilder();
		if(idList.size()>0) {
			sb.append("and class_id in (");
			
			for (int i = 0; i < idList.size(); i++) {
				
				sb.append(idList.get(i));
				
				if(i<idList.size()-1) {
					sb.append(", ");	
				}
			}
			sb.append(") ");
		} else {
			sb.append("and class_id in (0) ");
		}
		
		System.out.println(sb.toString());
		
		
		final String SQL = "SELECT room, subject1, subject2, prof FROM practice_table "
				+ "WHERE class_date = ? and  class_time = ? "
				+ sb.toString();
		
		List<PracticeProgressDto> ppds = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, classDate);
			pstmt.setInt(2, classTime);
			
			rs = pstmt.executeQuery();
			
			ppds = new ArrayList<>();
			
			for (int i = 0; i < 12; i++) {
				
				ppds.add(new PracticeProgressDto("","",""));
				
			}
			
			while (rs.next()) {

				if(rs.getInt(1) == 402) {
					ppds.get(0).setSubject1(rs.getString(2));
					ppds.get(0).setSubject2(rs.getString(3));
					ppds.get(0).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 403) {
					ppds.get(1).setSubject1(rs.getString(2));
					ppds.get(1).setSubject2(rs.getString(3));
					ppds.get(1).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 404) {
					ppds.get(2).setSubject1(rs.getString(2));
					ppds.get(2).setSubject2(rs.getString(3));
					ppds.get(2).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 405) {
					ppds.get(3).setSubject1(rs.getString(2));
					ppds.get(3).setSubject2(rs.getString(3));
					ppds.get(3).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 501) {
					ppds.get(4).setSubject1(rs.getString(2));
					ppds.get(4).setSubject2(rs.getString(3));
					ppds.get(4).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 502) {
					ppds.get(5).setSubject1(rs.getString(2));
					ppds.get(5).setSubject2(rs.getString(3));
					ppds.get(5).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 503) {
					ppds.get(6).setSubject1(rs.getString(2));
					ppds.get(6).setSubject2(rs.getString(3));
					ppds.get(6).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 504) {
					ppds.get(7).setSubject1(rs.getString(2));
					ppds.get(7).setSubject2(rs.getString(3));
					ppds.get(7).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 505) {
					ppds.get(8).setSubject1(rs.getString(2));
					ppds.get(8).setSubject2(rs.getString(3));
					ppds.get(8).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 506) {
					ppds.get(9).setSubject1(rs.getString(2));
					ppds.get(9).setSubject2(rs.getString(3));
					ppds.get(9).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 507) {
					ppds.get(10).setSubject1(rs.getString(2));
					ppds.get(10).setSubject2(rs.getString(3));
					ppds.get(10).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 508) {
					ppds.get(11).setSubject1(rs.getString(2));
					ppds.get(11).setSubject2(rs.getString(3));
					ppds.get(11).setProf(rs.getString(4));
				}
				
			}
		
			return ppds;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findPracticeNow : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null; // 실패시
	}
}
