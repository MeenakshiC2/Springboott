package com.example.demo2.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class Taskservice {
	@Autowired
	JdbcTemplate jc;
	public int projecttask(Taskpojo p) {
	int result=0;
		String checkuser=p.getCheckuser();
		String checkpwd=p.getCheckpwd();
         String sql="select * from Spring where username=? and password=? and role='p'";
         List<Map<String,Object>> alldata=new ArrayList<Map<String,Object>>();
 		alldata=jc.queryForList(sql,checkuser,checkpwd);
 		if(alldata.isEmpty())
 		{
 			System.out.println("empty String");
        }else
        {
        	String taskid=p.getTaskid();
        	String tasknam=p.getTaskname();
        	String des=p.getDescription();
        	String duration=p.getDuration();        	
        	String status=p.getStatus();
        	String assignedby=checkuser;
        	String prid=p.getProject_id();
        	 String sql1="select * from project_details where project_manager=? and project_id=? ";
        	 List<Map<String,Object>> alldata1=new ArrayList<Map<String,Object>>();
      	alldata1=jc.queryForList(sql1,checkuser,prid);
      	System.out.println(alldata1);
      	System.out.println("bgcfhgcfh");
      		if(alldata1==null){
      			System.out.println(alldata1+"tjutut");
      			return  566;
      		}
      		else	
      		{
      			System.out.println("hiiiii");
      		String in="insert into project_tasklist values(?,?,?,?,?,?,?)";
				result=jc.update(in,prid,taskid,tasknam,des,duration,status,assignedby);
           	return result;	
           	}
//      		return 1;
      		}
 		return 100;
        }
}