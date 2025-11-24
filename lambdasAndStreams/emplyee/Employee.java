package lambdasAndStreams.emplyee;

import java.util.*;
import java.util.stream.Collectors;

public class Employee {
  private int id;
  private String name;
  private int age;
  private long salary;
  private String gender;
  private String deptName;
  private String city;
  private int yearOfJoining;

  public Employee(
      int id,
      String name,
      int age,
      long salary,
      String gender,
      String deptName,
      String city,
      int yearOfJoining) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.gender = gender;
    this.deptName = deptName;
    this.city = city;
    this.yearOfJoining = yearOfJoining;
  }

  public static void main(String[] args) {
    List<Employee> empList = new ArrayList<>();
    empList.add(new Employee(1, "Yanksha", 28, 123, "F", "HR", "Blore", 2020));
    empList.add(new Employee(2, "Francesca", 29, 120, "F", "HR", "Hyderabad", 2015));
    empList.add(new Employee(3, "Ramesh", 30, 115, "M", "HR", "Chennai", 2014));
    empList.add(new Employee(4, "Melanie", 32, 125, "F", "HR", "Chennai", 2013));
    empList.add(new Employee(5, "Padma", 22, 150, "F", "IT", "Noida", 2013));
    empList.add(new Employee(6, "Milad", 27, 140, "M", "IT", "Gurugram", 2017));
    empList.add(new Employee(7, "Uzma", 26, 130, "F", "IT", "Pune", 2016));
    empList.add(new Employee(8, "Ali", 23, 145, "M", "IT", "Trivandam", 2015));
    empList.add(new Employee(9, "Ram", 25, 160, "M", "IT", "Blore", 2010));

    // group the employees by city
    Map<String, List<Employee>> empByCity =
        empList.stream().collect(Collectors.groupingBy(Employee::getCity));
    //    System.out.print("Employees grouped by city :: \n" + empByCity);

    // group the employees by age
    Map<Integer, List<Employee>> empByAge =
        empList.stream().collect(Collectors.groupingBy(Employee::getAge));
    //    System.out.print("Employees grouped by age :: \n" + empByAge);

    // find the count of male and female employees present in the organization
    Map<String, Long> noOfMaleAndFemaleEmployees =
        empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
    System.out.println(
        "Count of male and female employees present in the organization::"
            + noOfMaleAndFemaleEmployees);

    // find the count of male and female present in each department:
    Map<String, Map<String, Long>> genderMapInDepartment =
        empList.stream()
            .collect(
                Collectors.groupingBy(
                    Employee::getDeptName,
                    Collectors.groupingBy(Employee::getGender, Collectors.counting())));
    System.out.println("male and female count in each dept::" + genderMapInDepartment);

    // find the names of all distinct departments in the organization
    Set<String> departmentList =
        empList.stream().map(Employee::getDeptName).collect(Collectors.toSet());
    System.out.println("List of all departments::" + departmentList);

    // get employee list with age greater than 28
    List<Employee> employeesWithAgeGreaterThan28 =
        empList.stream().filter(emp -> emp.getAge() > 28).collect(Collectors.toList());
    System.out.println("employeesWithAgeGreaterThan28::" + employeesWithAgeGreaterThan28);

    // find maximum age employee in the organization
    Optional<Employee> oldestEmployee =
        empList.stream().max(Comparator.comparingInt(Employee::getAge));
    if (oldestEmployee.isPresent()) System.out.println("Oldest Employee :: " + oldestEmployee);

    // Get Average Age of Male and Female Employees

  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return id == employee.id
        && age == employee.age
        && salary == employee.salary
        && yearOfJoining == employee.yearOfJoining
        && Objects.equals(name, employee.name)
        && Objects.equals(gender, employee.gender)
        && Objects.equals(deptName, employee.deptName)
        && Objects.equals(city, employee.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, salary, gender, deptName, city, yearOfJoining);
  }

  @Override
  public String toString() {
    return "Employee{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", age="
        + age
        + ", salary="
        + salary
        + ", gender='"
        + gender
        + '\''
        + ", deptName='"
        + deptName
        + '\''
        + ", city='"
        + city
        + '\''
        + ", yearOfJoining="
        + yearOfJoining
        + '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public long getSalary() {
    return salary;
  }

  public void setSalary(long salary) {
    this.salary = salary;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getYearOfJoining() {
    return yearOfJoining;
  }

  public void setYearOfJoining(int yearOfJoining) {
    this.yearOfJoining = yearOfJoining;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
