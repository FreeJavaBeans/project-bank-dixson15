package controller;

import model_entity.Banker;
import model_entity.Employee;
import service.BankerService;

import java.util.List;

public class BankerController {

    private BankerService bankerService;

    public BankerController() {
        bankerService = new BankerService();
    }

    public String addEmployee(Banker employee){
        return bankerService.addEmployee(employee);
    }
    public List<Banker> getAllEmployee(){
        return bankerService.getAllEmployee();
    }
    public Employee getEmployeeByID(int emp_id){
        return bankerService.getEmployeeByID(emp_id);
    }
    public String updateEmployee(Banker banker){
        return bankerService.updateEmployee(banker);
    }
}
