package telran.employees.db;

import java.util.Iterator;

import telran.employees.*;

public class CompanyDbImpl implements Company{
    private CompanyRepository repository;

    
    public CompanyDbImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterator<Employee> iterator() {
        return repository.getEmployees().iterator();
    }

    @Override
    public void addEmployee(Employee empl) {
        repository.getEmployees().add(empl);
    }

    @Override
    public Employee getEmployee(long id) {
        Employee empl = null;
        boolean res = false;
        int i = 0;
        while(!res && i < repository.getEmployees().size() ) {
            if(repository.getEmployees().get(i).getId() == id) {
                empl = repository.getEmployees().get(i);
                res = true;
            }
            i++;
        }
        return empl;
    }

    @Override
    public Employee removeEmployee(long id) {
        Employee empl = null;
        boolean res = false;
        int i = 0;
        while(!res && i < repository.getEmployees().size() ) {
            if(repository.getEmployees().get(i).getId() == id) {
                empl = repository.getEmployees().get(i);
                repository.getEmployees().remove(i);
                res = true;
            }
            i++;
        }
        return empl;
    }

    @Override
    public int getDepartmentBudget(String department) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDepartments'");
    }

    @Override
    public String[] getDepartments() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDepartments'");
    }

    @Override
    public Manager[] getManagersWithMostFactor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getManagersWithMostFactor'");
    }

}