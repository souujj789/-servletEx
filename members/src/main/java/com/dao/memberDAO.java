package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vo.member;
//데이터베이스 연결, 목록(Select), 삽입(Insert), 삭제(Delete), 
//수정(Update, 1개를 조회 select)
//DAO에서는 변수와 VO만 사용을 하고 데이터형변환은 작업하지 않는다.
//데이터형변환이 필요하면 controller에서 작업

public class memberDAO {
	Connection connection;  //데이터베이스 연결 변수
	
	//데이터베이스 연결 메소드
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	//메소드 입력값과 출력값이 존재
	//목록(Select)
	//데이터베이스에서 모든 데이터를 읽어온다.
	public List<member> selectList() throws Exception {
		Statement stmt = null; //문법
		ResultSet rs = null; //결과값 저장
		String sql = null; //질의어 작성
		
		try {
			stmt = connection.createStatement(); //질의어생성
			sql = "SELECT mon, mname, email FROM members"; //테이블에서 특정필드만 읽어온다.
			rs = stmt.executeQuery(sql);
		
			//결과값을 vo에 저장하는 작업
			ArrayList<member> members = new ArrayList<member>();
		
			while(rs.next()) { //하나의 레코드씩 반복해서 읽어온다.
				member temp = new member(); //한레코드를 저장할 변수
				//저장할 VO    읽어온 테이블 필드
				temp.setMon(rs.getInt("mon"));
				temp.setMname(rs.getString("mname"));
				temp.setEmail(rs.getString("email"));
			
				members.add(temp);
			}
			//해당결과를 전달
			return members;
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
	}
	//삽입, 수정, 삭제는 int형으로 선언
	public int delete(int mon) throws Exception { //오류는 return 없어서
		PreparedStatement stmt = null; //문법
		String sql = null; //질의어 작성
		
		try {
			//삭제 작업
			sql = "DELETE FROM members WHERE mon=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, mon); //delete(인수를 사용)
			return stmt.executeUpdate();	//성공 1, 실패 0		
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
	}
	
	//삽입과 수정은 VO로 데이터를 저장받아서 처리
	public int insert(member member) throws Exception {
		PreparedStatement stmt = null; //문법
		String sql = null; //질의어 작성
		
		try {
			sql = "INSERT INTO members(mname,email,pwd) values (?,?,?)";
			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, member.getMname()); //isnert(vo인수를 이용)
			stmt.setString(2, member.getEmail());
			stmt.setString(3, member.getPwd());
			
			return stmt.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
	}
	
	//update 반드시 수정 자료가 필요(select-수정할 레코드 1개와서)
	//1개의 레코드만 검색-지정한 번호의 레코드를 읽어온다.
	public member selectOne(int mon) throws Exception {
		PreparedStatement stmt = null; //문법
		ResultSet rs = null; //결과값 저장
		String sql = null; //질의어 작성
		
		try {
			sql = "SELECT mon, mname, email FROM members WHERE mon=?"; //테이블에서 특정필드만 읽어온다.
			stmt = connection.prepareStatement(sql);; //질의어생성
			stmt.setInt(1, mon);
			rs = stmt.executeQuery();
				
			rs.next(); //1개를 읽어서
			
			member temp = new member(); //한레코드를 저장할 변수
			//저장할 VO    읽어온 테이블 필드
			temp.setMon(rs.getInt("mon"));
			temp.setMname(rs.getString("mname"));
			temp.setEmail(rs.getString("email"));
						
			//해당결과를 전달
			return temp;
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
	}
	
	public int update(member member) throws Exception {
		PreparedStatement stmt = null; //문법
		ResultSet rs = null; //결과값 저장
		String sql = null; //질의어 작성
		
		try {
			sql = "UPDATE members SET mname=?,email=?,pwd=? WHERE mon=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, member.getMname());
			stmt.setString(2, member.getEmail());
			stmt.setString(3,  member.getPwd());
			stmt.setInt(4, member.getMon());
			
			int a= stmt.executeUpdate();
			
			return a;
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
	}

}
