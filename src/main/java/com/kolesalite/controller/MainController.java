package com.kolesalite.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.kolesalite.model.Car;
import com.kolesalite.model.DataStorage;
import com.kolesalite.model.User;

import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML private VBox carsContainer;
    @FXML private Label welcomeLabel;
    @FXML private Button profileButton;
    @FXML private Button logoutButton;

    private DataStorage dataStorage = DataStorage.getInstance();

    @FXML
    public void initialize() {
        System.out.println("MainController инициализирован");
        loadCars();
        updateUserInfo();
    }

    private void loadCars() {
        carsContainer.getChildren().clear();
        List<Car> cars = dataStorage.getAllCars();

        for (Car car : cars) {
            carsContainer.getChildren().add(createCarCard(car));
        }
    }

    private HBox createCarCard(Car car) {
        HBox card = new HBox(15);
        card.setStyle("-fx-border-color: #ccc; -fx-border-width: 1; -fx-padding: 15; -fx-background-color: #f9f9f9;");
        card.setPrefHeight(120);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(180);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-border-color: #ddd; -fx-border-width: 1; -fx-background-color: #f0f0f0;");

        loadCarImage(imageView, car.getImageUrl(), car.getBrand() + " " + car.getModel());


        VBox infoBox = new VBox(5);
        Label title = new Label(car.getBrand() + " " + car.getModel() + " (" + car.getYear() + ")");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label price = new Label("Цена: " + car.getPrice() + " $");
        price.setStyle("-fx-font-size: 14px; -fx-text-fill: green;");

        Label details = new Label("Пробег: " + car.getMileage() + " км | Город: " + car.getCity());
        details.setStyle("-fx-font-size: 12px;");

        infoBox.getChildren().addAll(title, price, details);


        VBox buttonBox = new VBox(5);

        Button detailsButton = new Button("Подробнее");
        detailsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        detailsButton.setOnAction(e -> showCarDetails(car));

        User user = dataStorage.getCurrentUser();


        Button favoriteButton = new Button();
        if (user != null && user.isFavorite(car.getId())) {
            favoriteButton.setText("★ В избранном");
            favoriteButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        } else {
            favoriteButton.setText("☆ В избранное");
            favoriteButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        }
        favoriteButton.setOnAction(e -> toggleFavorite(car, favoriteButton));


        Button buyButton = new Button();
        if (user != null && user.isPurchased(car.getId())) {
            buyButton.setText("✓ Куплено");
            buyButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
            buyButton.setDisable(true);
        } else {
            buyButton.setText("Купить за " + car.getPrice() + " $");
            if (user != null && user.getBalance() >= car.getPrice()) {
                buyButton.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white;");
                buyButton.setDisable(false);
            } else {
                buyButton.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");
                buyButton.setDisable(true);
            }
        }
        buyButton.setOnAction(e -> buyCar(car, buyButton));

        buttonBox.getChildren().addAll(detailsButton, favoriteButton, buyButton);

        card.getChildren().addAll(imageView, infoBox, buttonBox);

        return card;
    }


    private void buyCar(Car car, Button buyButton) {
        User user = dataStorage.getCurrentUser();
        if (user == null) {
            showAlert("Войдите в систему для покупки");
            return;
        }

        if (user.isPurchased(car.getId())) {
            showAlert("Вы уже купили эту машину");
            return;
        }

        if (user.getBalance() < car.getPrice()) {
            showAlert("Недостаточно средств. Баланс: " + user.getBalance() + " $");
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Подтверждение покупки");
        confirmAlert.setHeaderText("Покупка " + car.getBrand() + " " + car.getModel());
        confirmAlert.setContentText("Цена: " + car.getPrice() + " $\nВаш баланс: " + user.getBalance() + " $\n\nПодтвердить покупку?");

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            boolean success = user.deductMoney(car.getPrice());
            if (success) {
                user.addPurchased(car.getId());
                buyButton.setText("✓ Куплено");
                buyButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                buyButton.setDisable(true);

                showAlert("Поздравляем! Вы купили " + car.getBrand() + " " + car.getModel() +
                        "\nСписано: " + car.getPrice() + " $\nОстаток: " + user.getBalance() + " $");
                System.out.println("Пользователь " + user.getUsername() + " купил машину ID: " + car.getId());
            } else {
                showAlert("Ошибка при списании средств");
            }
        }
    }

    private void loadCarImage(ImageView imageView, String imageUrl, String carName) {
        try {
            if (imageUrl == null || imageUrl.isEmpty()) {
                imageView.setImage(new Image("https://via.placeholder.com/180x120/cccccc/969696?text=" +
                        carName.replace(" ", "+"), 180, 120, true, true, true));
                return;
            }

            System.out.println("Загрузка фото для: " + carName);

            Image image = new Image(imageUrl, 180, 120, true, true, true);
            imageView.setImage(image);

            if (image.isError()) {
                System.err.println("ОШИБКА загрузки фото для " + carName);
                imageView.setImage(new Image("https://via.placeholder.com/180x120/ffcccc/cc0000?text=Ошибка+загрузки",
                        180, 120, true, true, true));
            } else {
                System.out.println("✓ Фото загружено для " + carName);
            }

        } catch (Exception e) {
            System.err.println("Исключение при загрузке фото для " + carName + ": " + e.getMessage());
            imageView.setImage(new Image("https://via.placeholder.com/180x120/ffcccc/cc0000?text=Ошибка",
                    180, 120, true, true, true));
        }
    }

    private void toggleFavorite(Car car, Button button) {
        User user = dataStorage.getCurrentUser();
        if (user == null) {
            showAlert("Войдите в систему для добавления в избранное");
            return;
        }

        if (user.isFavorite(car.getId())) {
            user.removeFavorite(car.getId());
            button.setText("☆ В избранное");
            button.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
            System.out.println("Удалено из избранного: " + car.getBrand() + " " + car.getModel());
        } else {
            user.addFavorite(car.getId());
            button.setText("★ В избранном");
            button.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
            System.out.println("Добавлено в избранное: " + car.getBrand() + " " + car.getModel());
        }
    }

    private void showCarDetails(Car car) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(car.getBrand() + " " + car.getModel());
        alert.setHeaderText("Цена: " + car.getPrice() + " $");

        ImageView detailImageView = new ImageView();
        detailImageView.setFitWidth(350);
        detailImageView.setFitHeight(250);
        detailImageView.setPreserveRatio(true);
        detailImageView.setStyle("-fx-border-color: #ddd; -fx-border-width: 1; -fx-border-radius: 5;");

        String imageUrl = car.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                Image detailImage = new Image(imageUrl, 350, 250, true, true, true);
                detailImageView.setImage(detailImage);
            } catch (Exception e) {
                System.err.println("Ошибка загрузки детального фото для " + car.getBrand());
                detailImageView.setImage(new Image("https://via.placeholder.com/350x250/cccccc/969696?text=" +
                        car.getBrand().replace(" ", "+"), 350, 250, true, true, true));
            }
        } else {
            detailImageView.setImage(new Image("https://via.placeholder.com/350x250/cccccc/969696?text=" +
                    car.getBrand().replace(" ", "+"), 350, 250, true, true, true));
        }

        alert.setGraphic(detailImageView);

        String contentText =
                "Год выпуска: " + car.getYear() + "\n" +
                        "Пробег: " + car.getMileage() + " км\n" +
                        "Город: " + car.getCity() + "\n" +
                        "ID автомобиля: " + car.getId();

        alert.setContentText(contentText);

        alert.getDialogPane().setMinHeight(400);
        alert.getDialogPane().setMinWidth(500);

        alert.showAndWait();
    }

    private void updateUserInfo() {
        User user = dataStorage.getCurrentUser();
        if (user != null) {
            welcomeLabel.setText("Добро пожаловать, " + user.getUsername() + "!");
        } else {
            welcomeLabel.setText("Добро пожаловать, гость!");
        }
    }

    @FXML
    private void handleProfile() {
        System.out.println("Кнопка 'Профиль' нажата");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/kolesalite/view/profile.fxml"));
            Stage stage = (Stage) profileButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("KolesaLite - Профиль");
            System.out.println("Профиль успешно открыт");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка: " + e.getMessage());
            showAlert("Ошибка открытия профиля: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogout() {
        System.out.println("Кнопка 'Выйти' нажата");
        dataStorage.logout();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/kolesalite/view/login.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("KolesaLite - Авторизация");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}