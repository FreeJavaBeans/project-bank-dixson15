package service;

import DAO.BankerDAO;
import model_entity.Banker;
import model_entity.Employee;

import java.util.List;

public class BankerService {

    private BankerDAO bankerDAO;

    public BankerService() {
        bankerDAO = new BankerDAO();
    }

    public String addEmployee(Banker employee){
        return bankerDAO.addEmployee(employee);
    }

    public List<Banker> getAllEmployee(){
        return bankerDAO.getAllEmployee();
    }

    public Employee getEmployeeByID(int emp_id){
        return bankerDAO.getEmployeeByID(emp_id);
    }

    public String updateEmployee(Banker banker){
        return bankerDAO.updateEmployee(banker);
    }
}
