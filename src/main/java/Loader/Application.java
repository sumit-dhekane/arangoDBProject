package Loader;

import arangodb.AQLQueries;
import arangodb.ArangoDBFactory;
import arangodb.CrudOperations;
import com.arangodb.entity.BaseDocument;
import entity.Employee;
import mockData.Mocker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

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
        AQLQueries aqlQueries=new AQLQueries(pwd);
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
                employeeList.add(new Employee(element.getAttribute("id"), department, firstName, lastName, designation, new Double(salary)));
            }
        }



//        arangoDBFactory.createDataBase("employee");
//        arangoDBFactory.createCollection("employee","employeeDetails");

        for (Employee employee : employeeList) {
            BaseDocument baseDocument = new BaseDocument();
            baseDocument.setKey(employee.getEmpID());
            baseDocument.addAttribute("firstName", employee.getFirstName());
            baseDocument.addAttribute("lastName", employee.getLastName());
            baseDocument.addAttribute("department", employee.getDepartment());
            baseDocument.addAttribute("designation", employee.getDesignation());
            baseDocument.addAttribute("salary", employee.getSalary());

            aqlQueries.insertData("employee", "employeeDetails",baseDocument);
        }

//        crudOperations.createDocument("employee", "employeeDetails", baseDocument);
//        crudOperations.deleteDocument("employee", "employeeDetails", "employees");
//        crudOperations.deleteDocument("employee", "employeeDetails", "employees");
        List<BaseDocument> baseDocuments= aqlQueries.getDocumentByFilter("employee", "employeeDetails","firstName","Jerry");
//        aqlQueries.deleteData("employee", "employeeDetails","designation","Developer");
        aqlQueries.getData("employee", "employeeDetails","designation","Developer","firstName");
        System.out.println(baseDocuments.get(0));

    }
}
