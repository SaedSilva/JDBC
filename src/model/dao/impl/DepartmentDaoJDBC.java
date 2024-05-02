package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {

    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()) {
                Department department = instantiateDepartment(rs);
                return department;
            }
            else return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Department> findAll() {
        ResultSet rs = null;
        Statement st = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM department");

            List<Department> list = new ArrayList<>();

            while (rs.next()) {
                Department department = instantiateDepartment(rs);
                list.add(department);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("Id"));
        department.setName(rs.getString("Name"));
        return department;
    }
}
