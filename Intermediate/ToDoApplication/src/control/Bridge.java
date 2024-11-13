package control;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Accounts;
import model.Task;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Bridge {

    public Boolean getMatchingAccount(String user, String password, Label error) throws Exception{

        File xmlAccounts = new File("src\\data\\Accounts.xml");

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
                    UserManagement userManagement = UserManagement.getInstance();
                    userManagement.setAccount(account);
                    return true;
                } else {
                    error.setText("Wrong Password");
                    error.setVisible(true);
                }
            } else {
                error.setText("User Does not Exist");
                error.setVisible(true);
            }
        }

        return false;
    }

    private ArrayList<Task> getTasks(String username) throws Exception{
        ArrayList<Task> task = new ArrayList();

        File xmlAccounts = new File("src\\data\\Tasks.xml");

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
                    LocalDate dateOfEvent = LocalDate.parse(date);

                    String importance = t.getElementsByTagName("importance").item(0).getTextContent();
                    String desc = t.getElementsByTagName("description").item(0).getTextContent();
                    
                    Task todo = new Task(user, dateOfEvent, importance, desc);
                    task.add(todo);
                }
            }
        }
        return task;
    }

    public Boolean checkUser(String user) throws ParserConfigurationException, SAXException, IOException{
        File xmlAccounts = new File("src\\data\\Accounts.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlAccounts);

        NodeList nodeList = document.getElementsByTagName("Account");

        for (int x = 0; x < nodeList.getLength(); x++){
            Node node = nodeList.item(x);
            Element elem = (Element) node;
            String username = elem.getElementsByTagName("username").item(0).getTextContent();
            
            if(user.equals(username)){
                return true;
            }
        }
        return false;
    }

    // Writes into the XML for Accounts then calls for writeToTask
    public void writeToFileAccounts(String user, String password, ArrayList<Task> tasks) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        File xmlAccounts = new File("src\\data\\Accounts.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlAccounts);

        Element root = document.getDocumentElement();

        Element accountN = document.createElement("Account");
        root.appendChild(accountN);

        Element usern = document.createElement("username");
        usern.setTextContent(user);
        accountN.appendChild(usern);

        Element passw = document.createElement("password");
        passw.setTextContent(password);
        accountN.appendChild(passw);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("src\\data\\Accounts.xml"));
        transformer.transform(source, result);

        if(tasks == null){
            tasks = new ArrayList<>();
        }

        writeToFileTasks(user, tasks);
    }

    // Writes into the XML for Tasks
    private void writeToFileTasks(String username, ArrayList<Task> tasks) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        File xmlAccounts = new File("src\\data\\Tasks.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlAccounts);

        Element root = document.getDocumentElement();

        Element taskSlot = document.createElement("slot");
        root.appendChild(taskSlot);

        Element user = document.createElement("user");
        user.setTextContent(username);
        taskSlot.appendChild(user);

        Element taskStorage = document.createElement("tasks");
        taskSlot.appendChild(taskStorage);

        if(!(tasks.isEmpty())){
            for(int x = 0; x < tasks.size(); x++){
                Element taskBox = document.createElement("task");

                Element title = document.createElement("title");
                title.setTextContent(tasks.get(x).getTitle());
                taskBox.appendChild(title);

                Element date = document.createElement("date");
                date.setTextContent(String.valueOf(tasks.get(x).getDate()));
                taskBox.appendChild(date);

                Element importance = document.createElement("importance");
                importance.setTextContent(tasks.get(x).getImportance());
                taskBox.appendChild(importance);

                Element desc = document.createElement("description");
                desc.setTextContent(tasks.get(x).getDescription());
                taskBox.appendChild(desc);

                taskSlot.appendChild(taskBox);
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("src\\data\\Tasks.xml"));
        transformer.transform(source, result);
    }

    public void writeToFileTasks(String username, Task tasks) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        File xmlAccounts = new File("src\\data\\Tasks.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlAccounts);

        NodeList e = document.getElementsByTagName("slot");

        Boolean existingAcc = false;
        int indexO = 0;

        for(int x = 0 ; x < e.getLength(); x++){
            Node node = e.item(x);
            Element elem = (Element) node;

            String user = elem.getElementsByTagName("user").item(0).getTextContent();
            if(username.equals(user)){
                existingAcc = true;
                indexO = x;
            }
        }

        Element root = document.getDocumentElement();
        Element taskSlot;
        Element taskStorage;

        if(existingAcc){
            Node node = e.item(indexO);
            taskSlot = (Element) node;
            root.appendChild(taskSlot);

            NodeList t = taskSlot.getElementsByTagName("tasks");
            Node ts = t.item(0);
            taskStorage = (Element) ts;

        } else {
            taskSlot = document.createElement("slot");
            root.appendChild(taskSlot);

            Element user = document.createElement("user");
            user.setTextContent(username);
            taskSlot.appendChild(user);

            taskStorage = document.createElement("tasks");
            taskSlot.appendChild(taskStorage);
        }

        Element taskBox = document.createElement("task");

        Element title = document.createElement("title");
        title.setTextContent(tasks.getTitle());
        taskBox.appendChild(title);

        Element date = document.createElement("date");
        date.setTextContent(String.valueOf(tasks.getDate()));
        taskBox.appendChild(date);

        Element importance = document.createElement("importance");
        importance.setTextContent(tasks.getImportance());
        taskBox.appendChild(importance);

        Element desc = document.createElement("description");
        desc.setTextContent(tasks.getDescription());
        taskBox.appendChild(desc);

        taskStorage.appendChild(taskBox);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("src\\data\\Tasks.xml"));
        transformer.transform(source, result);
    }


    // Method used for Main Landing
    // This loads all the task for the current user ( Earliest tasks )
    public void loadTasks(Accounts account, VBox storage){
        ArrayList<Task> tasks = account.getTask();
        
        Collections.sort(tasks);
        
        if(!tasks.isEmpty()){
            int max;

            if(tasks.size() > 3){
                max = 3;
            } else {
                max = tasks.size();
            }

            for(int x = 0; x < max; x++){
                HBox hBox = new HBox();
                hBox.setMinHeight(80);
                hBox.setMinWidth(200);

                switch (x) {
                    case 0 -> {
                        hBox.setStyle("-fx-background-color: #fdfdff;");
                        hBox.setOpacity(1);
                    }
                    case 1 -> {
                        hBox.setStyle("-fx-background-color: #f1f1f1;");
                        hBox.setOpacity(0.8);
                    }
                    default -> {
                        hBox.setStyle("-fx-background-color: #e0e0e0;");
                        hBox.setOpacity(0.5);
                    }
                }

                String test = hBox.getStyle() + "-fx-margin: 5px;";
                hBox.setStyle(test);

                hBox.setAlignment(Pos.CENTER_LEFT);
                hBox.setPadding(new Insets(10));

                Circle circle = new Circle();
                circle.setRadius(15);
                Paint paint;
                
                switch(tasks.get(x).getImportance()){
                    case "Important":
                        paint = Paint.valueOf("DARKRED");
                        circle.setFill(paint);
                        break;
                    case "Average":
                        paint = Paint.valueOf("DARKBLUE");
                        circle.setFill(paint);
                        break;
                    case "Low":
                        paint = Paint.valueOf("DARKGREEN");
                        circle.setFill(paint);
                        break;
                    default:
                        paint = Paint.valueOf("GREY");
                        circle.setFill(paint);
                        break;
                }
                hBox.getChildren().add(circle);

                

                VBox vBox = new VBox();
                vBox.setMinHeight(100);
                vBox.setMinWidth(157);

                vBox.setAlignment(Pos.CENTER_LEFT);


                Label title = new Label();
                title.setText(tasks.get(x).getTitle());
                title.setFont(new Font(20));

                Label date = new Label();
                date.setText(String.valueOf(tasks.get(x).getDate()));

                vBox.getChildren().add(title);
                vBox.getChildren().add(date);
                hBox.getChildren().add(vBox);

                storage.getChildren().add(hBox);
            }
        }
    }

    // Method for View Tasks
    public void displayAllTasks(ArrayList<Task> tasks, VBox storage){
        if(tasks.isEmpty()){
            Label label = new Label("No Tasks");
            storage.getChildren().add(label);
        } else {
            for(Task task : tasks){
                VBox mainSlotBox = new VBox();
                mainSlotBox.setMinHeight(227);
                mainSlotBox.setMinWidth(390);
                
                HBox topHeaderBox = new HBox();
                topHeaderBox.setMinHeight(50);
                topHeaderBox.setMinWidth(390);

                TextArea descTextArea = new TextArea();
                descTextArea.setMinHeight(126);
                descTextArea.setMinWidth(390);
                descTextArea.setEditable(false);
                descTextArea.setText(task.getDescription());

                HBox buttonBox = new HBox();
                buttonBox.setMinHeight(39);
                buttonBox.setMinWidth(390);

                VBox innerVBox = new VBox();
                innerVBox.setMinHeight(50);
                innerVBox.setMinWidth(231);

                TextField dateField = new TextField();
                dateField.setEditable(false);
                dateField.setText(String.valueOf(task.getDate()));

                TextField titleField = new TextField();
                titleField.setEditable(false);
                titleField.setText(task.getTitle());

                TextField importanceField = new TextField();
                importanceField.setEditable(false);
                importanceField.setText(task.getImportance());

                Button editBtn = new Button();
                editBtn.setText("Edit");

                Button deleteBtn = new Button();
                editBtn.setText("Delete");

                innerVBox.getChildren().add(titleField);
                innerVBox.getChildren().add(importanceField);

                topHeaderBox.getChildren().add(innerVBox);
                topHeaderBox.getChildren().add(dateField);

                buttonBox.getChildren().add(editBtn);
                buttonBox.getChildren().add(deleteBtn);

                mainSlotBox.getChildren().add(topHeaderBox);
                mainSlotBox.getChildren().add(descTextArea);
                mainSlotBox.getChildren().add(buttonBox);

                storage.getChildren().add(mainSlotBox);
            }
        }
    }
}
