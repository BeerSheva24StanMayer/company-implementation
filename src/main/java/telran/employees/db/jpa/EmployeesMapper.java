package telran.employees.db.jpa;

import org.json.JSONObject;

import telran.employees.*;

public class EmployeesMapper {
private static final String PACKAGE = "telran.employees.";
private static final String ENTITY_PACKAGE = "telran.employees.db.jpa.";
private static final String CLASS_NAME = "className";

public static Employee toEmployeeDtoFromEntity(EmployeeEntity entity) {
    String entityClassName = entity.getClass().getSimpleName();
    String dtoClassName = PACKAGE + entityClassName.replaceAll("Entity", "");
    JSONObject jsonObj = new JSONObject();
    jsonObj.put(CLASS_NAME, dtoClassName);
    entity.toJsonObject(jsonObj);
    return Employee.getEmployeeFromJSON(jsonObj.toString());
}
public static EmployeeEntity toEmployeeEntityFromDto(Employee empl) {
    String emplClassName = empl.getClass().getSimpleName();
    String entityClassName = ENTITY_PACKAGE + emplClassName + "Entity";
    try {
        @SuppressWarnings("unchecked")
        Class<EmployeeEntity> clazz = (Class<EmployeeEntity>) Class.forName(entityClassName);
        EmployeeEntity entity =  clazz.getConstructor().newInstance();
        entity.fromEmployeeDto(empl);
        return entity;
    } catch (Exception e) {
       throw new RuntimeException(e);
    }
}
}