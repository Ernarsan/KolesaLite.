package com.kolesalite.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.kolesalite.model.DataStorage;
import com.kolesalite.model.User;

import java.io.IOException;

public class AuthController {
    @FXML private TextField loginEmailField;
    @FXML private PasswordField loginPasswordField;
    @FXML private Label loginErrorLabel;
    @FXML private ImageView logoImageView;

    private DataStorage dataStorage = DataStorage.getInstance();

    @FXML
    public void initialize() {
        System.out.println("AuthController инициализирован");
        if (logoImageView != null) {
            System.out.println("Логотип IITU загружен");
        }
    }

    @FXML
    private void handleLogin() {
        String email = loginEmailField.getText().trim();
        String password = loginPasswordField.getText();

        System.out.println("Попытка входа: email=" + email + ", password=" + password);

        if (email.isEmpty() || password.isEmpty()) {
            loginErrorLabel.setText("Введите email и пароль");
            return;
        }

        User user = dataStorage.login(email, password);
        if (user != null) {
            System.out.println("Вход успешен: " + user.getUsername());
            showMainScene();
        } else {
            System.out.println("Вход не удался");
            loginErrorLabel.setText("Неверный email или пароль");
        }
    }

    private void showMainScene() {
        try {

            Stage currentStage = (Stage) loginEmailField.getScene().getWindow();
            currentStage.close();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/kolesalite/view/main.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("KolesaLite - IITU Project");
            stage.setScene(new Scene(root, 900, 600));
            stage.show();

            System.out.println("Главное окно открыто");
        } catch (IOException e) {
            e.printStackTrace();
            loginErrorLabel.setText("Ошибка загрузки главного окна");
        }
    }


    @FXML
    private void handleLogoClick() {
        System.out.println("Логотип IITU нажат");
        showLogoInfo();
    }

    private void showLogoInfo() {
        System.out.println("IITU - Международный университет информационных технологий");
    }
}