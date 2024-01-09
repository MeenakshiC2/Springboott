package com.example.demo2.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Taskcontroller {
@Autowired
Task_service tt;
	@PostMapping("project")
	public String project(@RequestBody Taskpojo p) {
     return tt.projecttask(p);
}

	@PostMapping("update")
	public String updatetask(@RequestBody Taskpojo p) {
return tt.updatetask(p);
}
	@PostMapping("delete")
	public String deletetask(Taskpojo p) {
    return tt.deletetask(p);
}
}