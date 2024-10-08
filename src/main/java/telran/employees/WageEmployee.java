package telran.employees;

import org.json.JSONObject;

public class WageEmployee extends Employee{
    private int wage;
    private int hours;

    public WageEmployee(){}
    public WageEmployee(long id, int basicSalary, String department, int wage, int hours) {
        super(id, basicSalary, department);
        this.wage = wage;
        this.hours = hours;
    }
    @Override
    public int computeSalary() {
        return super.computeSalary() + wage * hours;
    }

     @Override
     protected void fillJSON(JSONObject jsonObj) {
      //TODO
     }
     @Override
     protected void setObject(JSONObject jsonObj) {
         //TODO
      }
}