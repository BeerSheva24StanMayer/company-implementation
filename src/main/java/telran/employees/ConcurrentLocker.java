package telran.employees;

import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

public class ConcurrentLocker extends CompanyImpl{
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();


    public <T> T read(Supplier<T> func) {
        readLock.lock();
        T result;
        try {
            result = func.get();
        } finally {
            readLock.unlock();
        }
        return result;
    }

    public <T> T write(Supplier<T> func) {
        writeLock.lock();
        T result;
        try {
            result = func.get();
        } finally {
            writeLock.unlock();
        }
        return result;
    }

    @Override
    public Iterator<Employee> iterator() {
       return read(super::iterator);
    }

    @Override
    public void addEmployee(Employee empl) {
        write(() -> { super.addEmployee(empl); return null;});
    }

    @Override
    public Employee getEmployee(long id) {
        return read(() -> super.getEmployee(id));
    }

    @Override
    public Employee removeEmployee(long id) {
        return write(() -> super.removeEmployee(id));
    }

    @Override
    public int getDepartmentBudget(String department) {
        return read(() -> super.getDepartmentBudget(department));
    }

    @Override
    public String[] getDepartments() {
        return read(super::getDepartments);
    }

    @Override
    public Manager[] getManagersWithMostFactor() {
        return read(super::getManagersWithMostFactor);
    }

    @Override
    public void saveToFile(String fileName) {
        read(() -> {super.saveToFile(fileName); return null;});
    }

}