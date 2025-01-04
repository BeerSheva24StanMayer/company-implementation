package telran.employees.db.jpa;

import org.json.JSONObject;

import jakarta.persistence.*;
import telran.employees.Employee;
import telran.employees.WageEmployee;

public class WageEmployeeEntity extends EmployeeEntity {
    @Column
    private int wage;
    @Column
    private int hours;

    protected void fromEmployeeDto(Employee empl) {
        super.fromEmployeeDto(empl);
        wage = ((WageEmployee) empl).getWage();
        hours = ((WageEmployee) empl).getHours();
    }

    protected void toJsonObject(JSONObject jsonObj) {
        super.toJsonObject(jsonObj);
        jsonObj.put("wage", wage);
        jsonObj.put("hours", hours);
    }
}