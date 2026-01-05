package com.kolesalite.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.kolesalite.model.Car;
import com.kolesalite.model.DataStorage;
import com.kolesalite.model.User;
import java.io.IOException;


public class ProfileController {

    @FXML private Label usernameLabel;
    @FXML private Label emailLabel;
    @FXML private Label balanceLabel;
    @FXML private Label favoritesCountLabel;
    @FXML private Label purchasedCountLabel;
    @FXML private VBox favoritesContainer;
    @FXML private VBox purchasedContainer;
    @FXML private ImageView profileLogoImageView;

    private DataStorage dataStorage = DataStorage.getInstance();

    @FXML
    public void initialize() {
        System.out.println("ProfileController инициализирован");
        if (profileLogoImageView != null) {
            System.out.println("Логотип IITU загружен в профиле");

            profileLogoImageView.setOnMouseClicked(e -> handleLogoClick());
        }
        loadProfileData();
    }

    private void loadProfileData() {
        User user = dataStorage.getCurrentUser();
        if (user != null) {
            usernameLabel.setText("Имя: " + user.getUsername());
            emailLabel.setText("Email: " + user.getEmail());
            balanceLabel.setText("Баланс: " + String.format("%.2f", user.getBalance()) + " $");

            int favoriteCount = user.getFavoriteCarIds().size();
            int purchasedCount = user.getPurchasedCarIds().size();

            favoritesCountLabel.setText("Избранные машины: " + favoriteCount + " шт.");
            purchasedCountLabel.setText("Купленные машины: " + purchasedCount + " шт.");

            favoritesContainer.getChildren().clear();
            if (favoriteCount == 0) {
                Label emptyLabel = new Label("Нет избранных машин");
                emptyLabel.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
                favoritesContainer.getChildren().add(emptyLabel);
            } else {
                for (String carId : user.getFavoriteCarIds()) {
                    Car car = dataStorage.getCarById(carId);
                    if (car != null) {
                        Label carLabel = new Label("• " + car.getBrand() + " " + car.getModel() +
                                " - " + car.getPrice() + " ₸");
                        favoritesContainer.getChildren().add(carLabel);
                    }
                }
            }


            purchasedContainer.getChildren().clear();
            if (purchasedCount == 0) {
                Label emptyLabel = new Label("Нет купленных машин");
                emptyLabel.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
                purchasedContainer.getChildren().add(emptyLabel);
            } else {
                for (String carId : user.getPurchasedCarIds()) {
                    Car car = dataStorage.getCarById(carId);
                    if (car != null) {
                        Label carLabel = new Label("✓ " + car.getBrand() + " " + car.getModel() +
                                " - " + car.getPrice() + " ₸");
                        carLabel.setStyle("-fx-text-fill: #4CAF50; -fx-font-weight: bold;");
                        purchasedContainer.getChildren().add(carLabel);
                    }
                }
            }

            System.out.println("Профиль загружен для: " + user.getUsername());
        } else {
            System.out.println("Пользователь не авторизован в ProfileController");
            usernameLabel.setText("Имя: Не авторизован");
            emailLabel.setText("Email: Не авторизован");
            balanceLabel.setText("Баланс: 0 ₸");
            favoritesCountLabel.setText("Избранные машины: 0 шт.");
            purchasedCountLabel.setText("Купленные машины: 0 шт.");

            Label errorLabel = new Label("Войдите в систему для просмотра профиля");
            errorLabel.setStyle("-fx-text-fill: red; -fx-font-style: italic;");
            favoritesContainer.getChildren().clear();
            favoritesContainer.getChildren().add(errorLabel);
            purchasedContainer.getChildren().clear();
        }
    }

    @FXML
    private void handleBack() {
        System.out.println("Кнопка 'Назад' нажата");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/kolesalite/view/main.fxml"));
            Stage stage = (Stage) usernameLabel.getScene().getWindow();
            stage.setScene(new Scene(root, 900, 600));
            stage.setTitle("KolesaLite - Главная");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogoClick() {
        System.out.println("Логотип IITU нажат в профиле");
        showLogoMessage();
    }

    private void showLogoMessage() {
        System.out.println("Для перехода на сайт IITU откройте: https://iitu.edu.kz");


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("IITU - Международный университет информационных технологий");
        alert.setHeaderText("Официальный сайт университета");
        alert.setContentText("Перейдите по ссылке: https://iitu.edu.kz");
        alert.showAndWait();

    }
}