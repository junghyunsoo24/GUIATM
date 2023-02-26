package com.example.atm;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application  {
    Stage window;
    Scene homeScene, clientRegist, loginScene, accRegist, depositScene, withdrawScene, checkBalanceScene, transferAccountScene;
    TableView<Client> tableView;
    TextField nameInput, ssnInput, orderInput;
    int balance = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //홈화면
        window = primaryStage;

        Button register = new Button("(1)고객등록");
        register.setPrefSize(100, 70);
        register.setOnAction(e->window.setScene(clientRegist));

        Button login = new Button("(2)로그인");
        login.setPrefSize(100, 70);
        login.setOnAction(e->window.setScene(loginScene));

        Button newAcc = new Button("(3)계좌생성");
        newAcc.setPrefSize(100, 70);
        newAcc.setOnAction(e->window.setScene(accRegist));

        Button newDeposit = new Button("(4)입금");
        newDeposit.setPrefSize(100, 70);
        newDeposit.setOnAction(e->window.setScene(depositScene));

        Button newWithdraw = new Button("(5)출금");
        newWithdraw.setPrefSize(100, 70);
        newWithdraw.setOnAction(e->window.setScene(withdrawScene));

        Button newCheckBalance = new Button("(6)잔고조회");
        newCheckBalance.setPrefSize(100, 70);
        newCheckBalance.setOnAction(e->window.setScene(checkBalanceScene));

        Button newTransferAccount = new Button("(7)계좌이체");
        newTransferAccount.setPrefSize(100, 70);
        newTransferAccount.setOnAction(e->window.setScene(transferAccountScene));

        VBox homeLayout = new VBox();
        homeLayout.setPadding(new Insets(30));
        homeLayout.setSpacing(30);
        homeLayout.getChildren().addAll(register,login,newAcc,newDeposit,newWithdraw,newCheckBalance,newTransferAccount);

        homeScene = new Scene(homeLayout,400, 400);


        //고객등록
        TableColumn<Client, String> nameColumn = new TableColumn<>("NAME");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Client, Long> ssnColumn = new TableColumn<>("SSN");
        ssnColumn.setMinWidth(200);
        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("ssn"));

        TableColumn<Client, Integer> orderColumn = new TableColumn<>("ORDER");
        orderColumn.setMinWidth(150);
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));

        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);

        ssnInput = new TextField();
        ssnInput.setPromptText("SSN");

        orderInput = new TextField();
        orderInput.setPromptText("ORDER");

        Button addButton = new Button("추가");
        addButton.setOnAction(e -> addButtonClicked());
        Button buttonToHome2 = new Button("처음으로");
        buttonToHome2.setOnAction(e->window.setScene(homeScene));

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5,5,5,5));
        hBox.setSpacing(5);
        hBox.getChildren().addAll(nameInput,ssnInput,orderInput,addButton,buttonToHome2);

        tableView = new TableView<>();
        tableView.setItems(getClient());
        tableView.getColumns().addAll(nameColumn, ssnColumn,  orderColumn);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().add(tableView);
        layout.getChildren().add(hBox);
        clientRegist = new Scene(layout, 650, 400);


        //로그인화면
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label userID = new Label("ID");
        GridPane.setConstraints(userID, 0, 0);

        Label passwd = new Label("Password");
        GridPane.setConstraints(passwd, 0, 1);

        TextField IDInput = new TextField();
        GridPane.setConstraints(IDInput, 1, 0);

        TextField passwordInput = new TextField();
        GridPane.setConstraints(passwordInput, 1, 1);

        Button logOn = new Button("로그인");
        GridPane.setConstraints(logOn, 0, 2);
        logOn.setOnAction( e -> {
            if(IDInput.getText().equals("20191091") && (passwordInput.getText().equals("junghyunsoo"))) {
                System.out.println("ID: " + IDInput.getText());
                System.out.println("Password: " + passwordInput.getText());
                System.out.println("로그인이 완료되었습니다!");
            }
            else
                System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다. 다시입력하세요.");
        });

        Button buttonToHome1 = new Button("처음으로");
        buttonToHome1.setOnAction(e->window.setScene(homeScene));
        GridPane.setConstraints(buttonToHome1, 1, 2);

        gridPane.getChildren().addAll(userID, IDInput, passwd, passwordInput, logOn, buttonToHome1);
        loginScene = new Scene(gridPane, 400, 400);


        //계좌생성
        GridPane gridPane2 = new GridPane();
        gridPane2.setPadding(new Insets(10));
        gridPane2.setVgap(8);
        gridPane2.setHgap(10);

        Label newAccount = new Label("New Account");
        GridPane.setConstraints(newAccount, 0, 0);

        TextField accInput = new TextField();
        GridPane.setConstraints(accInput, 1, 0);

        Button makeAcc = new Button("계좌생성");
        GridPane.setConstraints(makeAcc, 0, 2);
        makeAcc.setOnAction( e -> {
            if(accInput.getText().length() == 10) {
                System.out.println("새로운 계좌 생성: " + accInput.getText());
                System.out.println("계좌 생성이 완료되었습니다!");
                System.out.println("현재 계좌: " + accInput.getText());
            }
            else
                System.out.println("계좌번호는 10자리입니다. 다시 입력하세요.");
        });

        Button buttonToHome3 = new Button("처음으로");
        buttonToHome3.setOnAction(e->window.setScene(homeScene));
        GridPane.setConstraints(buttonToHome3, 1, 2);

        gridPane2.getChildren().addAll(newAccount, accInput, makeAcc, buttonToHome3);
        accRegist = new Scene(gridPane2, 400, 400);


        //입금
        GridPane gridPane3 = new GridPane();
        gridPane3.setPadding(new Insets(10));
        gridPane3.setVgap(8);
        gridPane3.setHgap(10);

        Label deposit = new Label("입금할 금액");
        GridPane.setConstraints(deposit, 0, 0);

        TextField depositInput = new TextField();
        GridPane.setConstraints(depositInput, 1, 0);

        Button makeDeposit = new Button("입금");
        GridPane.setConstraints(makeDeposit, 0, 2);
        makeDeposit.setOnAction( e -> {
            if(depositInput.getText().equals(""))
                System.out.println("돈을 입금하지 않았습니다. 다시 입력하세요.");
            else {
                System.out.println("입금하기: " + depositInput.getText() + "원");
                balance = Integer.parseInt(balance + depositInput.getText());
                System.out.println("입금이 완료되었습니다!");
                System.out.println("현재 금액: " + balance + "원");
            }
        });

        Button buttonToHome4 = new Button("처음으로");
        buttonToHome4.setOnAction(e->window.setScene(homeScene));
        GridPane.setConstraints(buttonToHome4, 1, 2);

        gridPane3.getChildren().addAll(deposit, depositInput, makeDeposit, buttonToHome4);
        depositScene = new Scene(gridPane3, 400, 400);


        //출금
        GridPane gridPane4 = new GridPane();
        gridPane4.setPadding(new Insets(10));
        gridPane4.setVgap(8);
        gridPane4.setHgap(10);

        Label withdraw = new Label("출금할 금액");
        GridPane.setConstraints(withdraw, 0, 0);

        TextField withdrawInput = new TextField();
        GridPane.setConstraints(withdrawInput, 1, 0);

        Button makeWithdraw = new Button("출금");
        GridPane.setConstraints(makeWithdraw, 0, 2);
        makeWithdraw.setOnAction( e -> {
            if(depositInput.getText().equals(""))
                System.out.println("돈을 출금하지 않았습니다. 다시 입력하세요.");
            else{
                System.out.println("출금하기: " + withdrawInput.getText() + "원");
                int num = Integer.parseInt(withdrawInput.getText());
                if(balance < num)
                    System.out.println("잔액이 " + (num - balance) + "원 부족합니다. 다시 입력하세요.");
                else {
                    balance = balance - num;
                    System.out.println("출금이 완료되었습니다!");
                    System.out.println("현재 금액: " + balance + "원");
                }
            }
        });

        Button buttonToHome5 = new Button("처음으로");
        buttonToHome5.setOnAction(e->window.setScene(homeScene));
        GridPane.setConstraints(buttonToHome5, 1, 2);

        gridPane4.getChildren().addAll(withdraw,withdrawInput, makeWithdraw, buttonToHome5);
        withdrawScene = new Scene(gridPane4, 400, 400);


        //잔고조회
        GridPane gridPane5 = new GridPane();
        gridPane5.setPadding(new Insets(10));
        gridPane5.setVgap(8);
        gridPane5.setHgap(10);

        Button makeCheckBalance = new Button("잔고조회");
        GridPane.setConstraints(makeCheckBalance, 0, 1);
        makeCheckBalance.setOnAction( e -> {
            System.out.println("남은 잔고: " + balance + "원");
        });

        Button buttonToHome6 = new Button("처음으로");
        buttonToHome6.setOnAction(e->window.setScene(homeScene));
        GridPane.setConstraints(buttonToHome6, 0, 2);

        gridPane5.getChildren().addAll(makeCheckBalance, buttonToHome6);
        checkBalanceScene = new Scene(gridPane5, 400, 400);


        //계좌이체
        GridPane gridPane6 = new GridPane();
        gridPane6.setPadding(new Insets(10));
        gridPane6.setVgap(8);
        gridPane6.setHgap(10);

        Label transferAccount1 = new Label("받는사람 계좌번호");
        GridPane.setConstraints(transferAccount1, 0, 0);

        Label transferAccount2 = new Label("보낼 금액");
        GridPane.setConstraints(transferAccount2, 0, 1);

        TextField transferAccount1Input = new TextField();
        GridPane.setConstraints(transferAccount1Input, 1, 0);

        TextField transferAccount2Input = new TextField();
        GridPane.setConstraints(transferAccount2Input, 1, 1);

        Button makeTransferAccount = new Button("계좌이체");
        GridPane.setConstraints(makeTransferAccount, 0, 2);

        makeTransferAccount.setOnAction( e -> {
            if(transferAccount1Input.getText().length() == 10){
                if(transferAccount2Input.getText().equals(""))
                    System.out.println("돈을 보내지 않았습니다. 다시 입력하세요.");
                else{
                    System.out.println("받는사람 계좌번호: " + transferAccount1Input.getText());
                    int num2 = Integer.parseInt(transferAccount2Input.getText());
                    if(num2 > balance)
                        System.out.println("잔액이 " + (num2 - balance) + "원 부족합니다. 다시 입력하세요.");
                    else {
                        balance = balance - num2;
                        System.out.println("보내는 금액: " + num2 + "원");
                        System.out.println("남은 금액: " + balance + "원");
                        System.out.println("계좌이체가 완료되었습니다!");
                    }
                }
            }
            else
                System.out.println("계좌번호는 10자리입니다. 다시 입력하세요.");
        });

        Button buttonToHome7 = new Button("처음으로");
        buttonToHome7.setOnAction(e->window.setScene(homeScene));
        GridPane.setConstraints(buttonToHome7, 1, 2);

        gridPane6.getChildren().addAll(transferAccount1,transferAccount2,transferAccount1Input,transferAccount2Input,makeTransferAccount,buttonToHome7);
        transferAccountScene = new Scene(gridPane6, 400, 400);


        //처음부터 시작
        window.setScene(homeScene);
        window.setTitle("ATM");
        window.show();
    }


    //고객등록 method
    private void addButtonClicked() {
        Client client = new Client();
        client.setName(nameInput.getText());
        client.setSsn(Long.parseLong(ssnInput.getText()));
        client.setOrder(Integer.parseInt(orderInput.getText()));
        tableView.getItems().addAll(client);
        nameInput.clear();
        ssnInput.clear();
        orderInput.clear();
    }
    public ObservableList<Client> getClient(){
        ObservableList<Client> clients = FXCollections.observableArrayList();
        clients.add(new Client("임꺽정", 1234567L, 1));
        clients.add(new Client("홀길동", 1234568L, 2));
        clients.add(new Client("김삿갓", 1234569L, 3));
        return clients;
    }
}