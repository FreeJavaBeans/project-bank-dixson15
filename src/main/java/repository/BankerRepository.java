package repository;

import model_entity.Banker;
import model_entity.Employee;

import java.util.List;

public interface BankerRepository {

    String addEmployee(Banker employee);
    List<Banker> getAllEmployee();
    Employee getEmployeeByID(int emp_id);
    String updateEmployee(Banker banker);
}
