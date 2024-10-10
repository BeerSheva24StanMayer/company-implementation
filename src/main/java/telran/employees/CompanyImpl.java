package telran.employees;
import java.util.*;

import telran.io.Persistable;

public class CompanyImpl implements Company, Persistable {
    private TreeMap<Long, Employee> employees = new TreeMap<>();
    private HashMap<String, List<Employee>> departments = new HashMap<>();
    private TreeMap<Float, List<Manager>> managersFactor = new TreeMap<>();

    private class CompanyIterator implements Iterator<Employee> {
        private Iterator<Employee> it = employees.values().iterator();
        private Employee prev = null;

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Employee next() {
            return prev = it.next();
        }

        @Override
        public void remove() {
            it.remove();
            removeFromDepartment(prev);
            removeFromManager(prev);
        }

    }

    @Override
    public Iterator<Employee> iterator() {
        return new CompanyIterator();
    }

    @Override
    public void addEmployee(Employee empl) {
        Employee ifEmployee = employees.putIfAbsent(empl.getId(), empl);
        if (ifEmployee != null) {
            throw new IllegalStateException();
        } else {
            addToDepartments(empl);
            addToManagers(empl);
        }
    }

    @Override
    public Employee getEmployee(long id) {
        return employees.get(id);
    }

    @Override
    public Employee removeEmployee(long id) {
        Employee removedEmployee = employees.remove(id);
        if (removedEmployee == null) {
            throw new NoSuchElementException();
        } else {
            removeFromDepartment(removedEmployee);
            removeFromManager(removedEmployee);
        }
        return removedEmployee;
    }

    @Override
    public int getDepartmentBudget(String department) {
        int budget = 0;
        List<Employee> listOfEmpl = departments.get(department);
        if (listOfEmpl != null) {
            budget = listOfEmpl.stream().mapToInt(n -> n.computeSalary()).sum();
        }
        return budget;
    }

    @Override
    public String[] getDepartments() {
        return departments.keySet().stream().sorted().toArray(String[]::new);
    }

    @Override
    public Manager[] getManagersWithMostFactor() {
        Manager[] res = new Manager[0];
        if (!managersFactor.isEmpty()) {
            res = managersFactor.lastEntry().getValue().toArray(Manager[]::new);
        }
        return res;
    }

    private void addToManagers(Employee empl) {
        if (empl instanceof Manager manager) {
            managersFactor.computeIfAbsent(manager.getFactor(), n -> new ArrayList<>()).add(manager);
        }
    }

    private void removeFromManager(Employee empl) {
        if (empl instanceof Manager manager) {
            Float factor = manager.getFactor();
            if (factor != null) {
                managersFactor.get(manager.getFactor()).remove(manager);
                if (managersFactor.get(factor).isEmpty())
                    managersFactor.remove(factor);
            }

        }
    }

    private void addToDepartments(Employee empl) {
        departments.computeIfAbsent(empl.getDepartment(), n -> new ArrayList<>()).add(empl);
    }

    private void removeFromDepartment(Employee empl) {
        String department = empl.getDepartment();
        if (department != null) {
            departments.get(department).remove(empl);
            if (departments.get(department).isEmpty()) {
                departments.remove(department);
            }
        }
    }

    @Override
    public void saveToFile(String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveToFile'");
    }
    @Override
    public void restoreFromFile(String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'restoreFromFile'");
    }


}