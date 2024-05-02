package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class ProgramSeller {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("---Teste #1: Seller FindByID---");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println();

        System.out.println("---Teste #2: Seller FindByDepartment---");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);
        System.out.println();

        System.out.println("---Teste #3: Seller FindAll---");
        list = sellerDao.findAll();
        list.forEach(System.out::println);
        System.out.println();

        System.out.println("---Teste #4: Seller Insert---");
        Seller newseller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(newseller);
        System.out.println("Inserted! New id: " + newseller.getId());
        System.out.println();

        System.out.println("---Teste #5: Seller Update---");
        seller = sellerDao.findById(1);
        seller.setName("Martha Wayne");
        sellerDao.update(seller);
        System.out.println("Update Completed");
    }
}