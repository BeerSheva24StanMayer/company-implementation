package telran.employees.db.jpa;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;

import telran.employees.CompanyTest;
import telran.employees.db.CompanyDbImpl;
import telran.employees.db.CompanyRepository;

public class CompanyJpaTest extends CompanyTest {
    HashMap<String, Object> properties = new HashMap<>() {
        {
            put("hibernate.hdm2ddl.auto", "create");
        }
    };

    @BeforeEach
    @Override
    protected void setCompany() {
        CompanyRepository repository = new CompanyRepositoryJpaImpl(new EmployeesTestPersistenceUnitInfo(), properties);
        company = new CompanyDbImpl(repository);
        super.setCompany();
    }
}
