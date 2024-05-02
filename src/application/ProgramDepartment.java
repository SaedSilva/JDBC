package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class ProgramDepartment {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("---Teste #1: DepartmentFindAll");
        List<Department> list = departmentDao.findAll();
        list.forEach(System.out::println);
    }
}