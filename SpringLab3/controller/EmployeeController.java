package com.example.SpringLab3.controller;
import com.example.SpringLab3.model.ApiResponse;
import com.example.SpringLab3.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/emp")
public class EmployeeController {
    private ArrayList<Employee> employees=new ArrayList<>();

    @GetMapping
    public ResponseEntity getEmployees(){
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody @Valid Employee emp, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        employees.add(emp);
        ApiResponse message=new ApiResponse("New employee added !",201);
        return ResponseEntity.status(201).body(message);
    }
    @PutMapping("/{index}")
    public ResponseEntity updateEmployee(@RequestBody @Valid Employee emp
            ,@PathVariable int index,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            ApiResponse msg=new ApiResponse(message,400);
            return ResponseEntity.status(400).body(msg);
        }
        if(index>=employees.size()){
            ApiResponse msg=new ApiResponse("Invalid index",400);
            return ResponseEntity.status(400).body(msg);
        }
        employees.set(index,emp);
        ApiResponse msg=new ApiResponse("employee updated",400);
        return ResponseEntity.status(201).body(msg);
    }

    @DeleteMapping("/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index){
        if(index>=employees.size()){
            ApiResponse msg=new ApiResponse("Invalid index",400);
            return ResponseEntity.status(400).body(msg);
        }
        employees.remove(index);
        ApiResponse msg =new ApiResponse("Employee deleted",400);
        return ResponseEntity.status(200).body(msg);
    }

    @PostMapping("/annualLeave")
    public ResponseEntity getAnnualLeave(@RequestParam int id){
        Employee em;
        for (int i = 0; i <employees.size() ; i++) {
            if(employees.get(i).getId()==id){
                em=employees.get(i);
                if(em.getOnLeave().equals("false")) {
                    em.setOnLeave("true");
                    em.setAnnualLeave(em.getAnnualLeave() - 1);
                    ApiResponse msg = new ApiResponse("Employee on Leave", 400);
                    return ResponseEntity.status(200).body(msg);
                }
                break;
            }
            }
        ApiResponse msg =new ApiResponse("not allowed, Employee already onLeave",400);
        return ResponseEntity.status(200).body(msg);
    }
}
