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
        System.out.println();

        System.out.println("---Teste #2: DepartmentFindById");
        Department department = departmentDao.findById(2);
        System.out.println(department);
        System.out.println();

        System.out.println("---Teste #3: DepartmentInsert");
        Department newdepartment = new Department(null, "Gym");
        departmentDao.insert(newdepartment);
        System.out.println("Insert sucess! " + newdepartment.getId());

        System.out.println("---Teste #4: DepartmentUpdate");
        newdepartment = departmentDao.findById(1);
        newdepartment.setName("Music");
        departmentDao.update(newdepartment);
        System.out.println("Update Sucess!");

    }
}