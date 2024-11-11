package control;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Accounts;
import model.Task;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Accounts;

public class Bridge {

    private void getMatchingAccount(String user, String password) throws Exception{
        File xmlAccounts = new File("./data/Accounts.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(xmlAccounts);

        NodeList nodeList = document.getElementsByTagName("Account");

        for (int x = 0; x < nodeList.getLength(); x++){
            Node node = nodeList.item(x);
            Element elem = (Element) node;
            String username = elem.getElementsByTagName("username").item(0).getTextContent();
            String pass = elem.getElementsByTagName("password").item(0).getTextContent();
            if(user.equals(username)){
                if(password.equals(pass)){
                    ArrayList<Task> task = getTasks(username);
                    Accounts account = new Accounts(username,password,task);
                } else {
                    //Wrong Password
                    System.out.print("Wrong Password");
                }
            } else {
                //User does not Exist Error prompt
                System.out.print("User Does not Exist");
            }
        }
    }

    private ArrayList<Task> getTasks(String username) throws Exception{
        ArrayList<Task> task = new ArrayList();

        File xmlAccounts = new File("./data/Tasks.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(xmlAccounts);

        NodeList nodeList = document.getElementsByTagName("slot");

        for (int x = 0; x < nodeList.getLength(); x++){
            Node node = nodeList.item(x);
            Element elem = (Element) node;
            String user = elem.getElementsByTagName("user").item(0).getTextContent();

            if(user.equals(username)){
                for(int y = 0; y< elem.getElementsByTagName("task").getLength(); y++){
                    Element t = (Element) elem.getElementsByTagName("task").item(y);
                    
                    String date =  t.getElementsByTagName("date").item(0).getTextContent();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
                    LocalDate dateOfEvent = LocalDate.parse(date,formatter);

                    String importance = t.getElementsByTagName("importance").item(0).getTextContent();
                    String desc = t.getElementsByTagName("description").item(0).getTextContent();
                    
                    Task todo = new Task(user, dateOfEvent, importance, desc);
                    task.add(todo);
                }
            }
        }
        return task;
    }
}
