package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date; //sql로 utilX
import java.util.List;

import com.vo.HealthVO;

public class HealthDAO {
	Connection connection;//데이터베이스 연결을 위한 전역변수
	
	public void setConnection(Connection connection) {//데이터베이스 연결
		this.connection = connection;
	}
	
	//질의어 작업(검색, 삽입, 삭제, 수정(1개 검색)) - 총 5개 메소드 필요
	//전체 검색
	//?없으면 Statement선언, ?있으면 PreparedStatement 선언
	//select를 사용하는 곳에는 ResultSet rs = null;
	//전체 검색은 List<com.vo> 전달
	public List<HealthVO> selectList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		sql = "SELECT * FROM health";
		//?없으면 createStatement()를 생성

		stmt = connection.createStatement();//데이터베이스 구동
		rs = stmt.executeQuery(sql);
			
		//결과처리(변수명은 클래스명을 소문자로 구성
		ArrayList<HealthVO> healthvo = new ArrayList<HealthVO>();
			
		//여러개의 결과값이 존재하면
		while (rs.next()) {
			HealthVO temp = new HealthVO();
			//저장작업
			temp.setId(rs.getInt("id"));
			temp.setHdate(rs.getDate("hdate"));//날짜는 date오류시 string으로 사용
			temp.setHweight(rs.getInt("hweight"));
			temp.setHheight(rs.getInt("hheight"));
			temp.setHblood(rs.getInt("hblood"));
			temp.setEid(rs.getInt("eid"));
			
			healthvo.add(temp);//배열에 저장
		}
		return healthvo;
	}
	//삽입, 수정, 삭제 int 선언
	//삽입, 수정(인수를 healthVO로 받아서 입력)
	public int insert(HealthVO healthvo)  throws Exception {
		PreparedStatement stmt = null;
		String sql = null;

		sql = "INSERT INTO health (hdate,hweigh,hheigh,hbloo,eid) "
				+ "VALUES (?,?,?,?,?)";

		//?있으면 prepareStatement()를 생성
		stmt = connection.prepareStatement(sql);
		
		stmt.setDate(1, (Date)healthvo.getHdate());
		stmt.setInt(2, healthvo.getHweight());
		stmt.setInt(3, healthvo.getHheight());
		stmt.setInt(4, healthvo.getHblood());
		stmt.setInt(5, healthvo.getEid());
		
		return stmt.executeUpdate();
	}
	//삭제(번호를 받아서 작업)
	public int delete(int id) throws Exception{
		PreparedStatement stmt = null;
		String sql = null;
		
		sql ="DELETE FROM health WHERE id = ?";
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		return stmt.executeUpdate();
	}
	//수정(번호를 받아서 작업)
	public HealthVO selectOne(int id) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		sql = "SELECT * FROM health WHERE id=?";
		//?없으면 createStatement()를 생성

		stmt = connection.prepareStatement(sql); //데이터베이스 구동
		stmt.setInt(1, id);
		
		rs = stmt.executeQuery();

		rs.next();
		HealthVO temp = new HealthVO();			
		//저장작업
		temp.setId(rs.getInt("id"));
		temp.setHdate(rs.getDate("hdate"));
		temp.setHweight(rs.getInt("hweight"));
		temp.setHheight(rs.getInt("hheight"));				
		temp.setHblood(rs.getInt("hblood"));
		temp.setEid(rs.getInt("eid")); //옆에 set이름을 보고 입력, 데이터형만 정확하게
								
		return temp;
	}//selectOne(수정)
	//HealthVO를 받아서 처리, int 선언
	public int update(HealthVO healthvo) throws Exception {
		PreparedStatement stmt = null;
		String sql = null;
		
		sql="UPDATE health SET hdate = ?, hweight = ?, hheight = ?, hblood = ?, ";
		sql+=" eid = ? WHERE id = ?";
		stmt = connection.prepareStatement(sql);
		stmt.setDate(1, (Date)healthvo.getHdate());
		stmt.setInt(2, healthvo.getHweight());
		stmt.setInt(3, healthvo.getHheight());
		stmt.setInt(4, healthvo.getHblood());
		stmt.setInt(5, healthvo.getEid());
		stmt.setInt(6, healthvo.getId());
		
		return stmt.executeUpdate();
	}//update
}