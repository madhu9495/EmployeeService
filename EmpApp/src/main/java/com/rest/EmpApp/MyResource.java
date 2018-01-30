package com.rest.EmpApp;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


import com.rest.EmpApp.dao.EmployeeDAO;
import com.rest.EmpApp.model.Employee;


@Path("/employees")
public class MyResource {
	
	EmployeeDAO dao=new EmployeeDAO();


	@GET
    @Produces("application/json")
    public List<Employee> getEmployee() {
   
        List<Employee>employees = dao.getEmployees();
        return employees;
    }
	
	@GET
	@Path("/{id}")
    @Produces("application/json")
    public Response getEmployeebyID(@PathParam("id") Integer id) {
     
		Employee emp=dao.getEmployee(id);
            
        return Response.ok(emp).build();
    }
	
	@POST
    @Path("/create")
	@Produces("application/json")
    public Response addEmployee(Employee emp){
       
		dao.addEmployee(emp);
        
        return Response.ok(emp).build();
    }
	
	@PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateEmployee(@PathParam("id") int id, Employee emp){
        int count = dao.updateEmployee(id, emp);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
	
	@DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteEmployee(@PathParam("id") int id){
        int count = dao.deleteEmployee(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
