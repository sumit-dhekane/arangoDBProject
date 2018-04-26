package Loader;

import arangodb.ArangoDBFactory;
import arangodb.CrudOperations;
import com.arangodb.entity.BaseDocument;
import entity.Employee;
import mockData.Mocker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {

        Mocker mocker = new Mocker();
        ClassLoader classLoader = mocker.getClass().getClassLoader();
        String configFile = classLoader.getResource("Config.properties").getPath();
        String employeeDataFile = classLoader.getResource("EmployeeDetails.xml").getPath();
        String pwd = null;
        pwd = mocker.getConfig("dbpassword", configFile);
        ArangoDBFactory arangoDBFactory = new ArangoDBFactory(pwd);
        CrudOperations crudOperations = new CrudOperations(pwd);
        ArrayList<Employee> employeeList = new ArrayList<Employee>();


        Document document = mocker.getMockData(employeeDataFile);

        NodeList nList = document.getElementsByTagName("employee");


        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);


            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) nNode;

                String firstName = element.getElementsByTagName("firstname").item(0).getTextContent();
                String lastName = element.getElementsByTagName("lastname").item(0).getTextContent();
                String salary = element.getElementsByTagName("salary").item(0).getTextContent();
                String department = element.getElementsByTagName("department").item(0).getTextContent();
                String designation = element.getElementsByTagName("designation").item(0).getTextContent();
                System.out.println(firstName + lastName + salary + department + designation);
                employeeList.add(new Employee(element.getAttribute("id"), department, firstName, lastName, designation, new Double(salary)));
            }
        }

        BaseDocument baseDocument = new BaseDocument();
        baseDocument.setKey("employees");

//        arangoDBFactory.createDataBase("employee");
//        arangoDBFactory.createCollection("employee","employeeDetails");

        for (Employee employee : employeeList) {
            baseDocument.addAttribute(employee.getEmpID(), employee);

        }

        crudOperations.createDocument("employee", "employeeDetails", baseDocument);


    }
}
