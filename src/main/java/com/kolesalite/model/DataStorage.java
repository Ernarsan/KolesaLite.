package com.kolesalite.model;

import java.util.*;

public class DataStorage {
    private static DataStorage instance;
    private List<Car> cars;
    private Map<String, User> users;
    private User currentUser;

    private DataStorage() {
        cars = new ArrayList<>();
        users = new HashMap<>();
        currentUser = null;
        initCars();
        initTestUsers();
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    private void initCars() {

        cars.add(new Car("1", "Toyota", "Camry", 2020, 7200000, 50000, "Almaty", "https://images.hgmsites.net/hug/toyota-camry_100720257_h.jpg"));
        cars.add(new Car("2", "Hyundai", "Sonata", 2019, 5760000, 60000, "Astana", "https://i.artfile.ru/2880x1800_1462053_[www.ArtFile.ru].jpg"));
        cars.add(new Car("3", "Kia", "Rio", 2018, 4320000, 70000, "Shymkent", "https://www.larevueautomobile.com/images/fiche-technique/2018/Kia/Rio/Kia-_Rio_001.jpg"));
        cars.add(new Car("4", "Lexus", "RX", 2021, 16800000, 20000, "Almaty", "https://images.ctfassets.net/c9t6u0qhbv9e/2021LexusRXTestDriveReviewsummary/d9ff0b9c1b2e88d10a734a1bae72ad78/2021_Lexus_RX_Test_Drive_Review_summaryImage.jpeg"));
        cars.add(new Car("5", "BMW", "X5", 2020, 19200000, 30000, "Astana", "https://mediacloud.carbuyer.co.uk/image/private/s--V3Ik1_FY--/v1579624421/carbuyer/2018/06/p90303990_highres.jpg"));
        cars.add(new Car("6", "Mercedes", "E-Class", 2019, 14400000, 40000, "Atyrau", "https://i.ytimg.com/vi/ubBHCYCJ1ag/maxresdefault.jpg"));
        cars.add(new Car("7", "Toyota", "RAV4", 2021, 10560000, 25000, "Almaty", "https://images.cars.com/cldstatic/wp-content/uploads/toyota-rav4-prime-2021-01-angle--exterior--front--grey.jpg"));
        cars.add(new Car("8", "Hyundai", "Tucson", 2020, 8640000, 35000, "Karaganda", "https://media.autoexpress.co.uk/image/private/s--DCMBxn5H--/t_content-image-full-desktop@2/v1606215972/autoexpress/2020/11/Hyundai%20Tucson%20SUV%202020-23.jpg"));
        cars.add(new Car("9", "Kia", "Sportage", 2019, 7680000, 45000, "Astana", "https://i.ytimg.com/vi/9Fmr_84DSe4/maxresdefault.jpg"));
        cars.add(new Car("10", "Nissan", "Qashqai", 2018, 6720000, 55000, "Almaty", "https://media1.autohaus.de/sixcms/media.php/5172/thumbnails/Nissan-Qashqai-2018-5.jpg.14171070.jpg"));
        cars.add(new Car("11", "Toyota", "Corolla", 2019, 6240000, 45000, "Almaty", "https://static0.carbuzzimages.com/wordpress/wp-content/uploads/gallery-images/original/511000/700/511798.jpg"));
        cars.add(new Car("12", "Hyundai", "Elantra", 2020, 6720000, 40000, "Astana", "https://s1.cdn.autoevolution.com/images/gallery/HYUNDAI-Elantra-5845_41.jpg"));
        cars.add(new Car("13", "Kia", "Forte", 2018, 5280000, 55000, "Shymkent", "https://static0.topspeedimages.com/wordpress/wp-content/uploads/jpg/201601/kia-forte-12.jpg"));
        cars.add(new Car("14", "BMW", "3 Series", 2021, 15360000, 25000, "Almaty", "https://media.ed.edmunds-media.com/bmw/3-series/2018/oem/2018_bmw_3-series_sedan_340i_fq_oem_10_1280.jpg"));
        cars.add(new Car("15", "Mercedes", "C-Class", 2020, 16800000, 30000, "Astana", "https://media.ed.edmunds-media.com/mercedes-benz/c-class/2019/fd/2019_mercedes-benz_c-class_actf34_fd_618181_1600.jpg"));
        cars.add(new Car("16", "Nissan", "Sentra", 2019, 7680000, 48000, "Atyrau", "https://www.carscoops.com/wp-content/uploads/2019/11/2020-Nissan-Sentra-LA.jpg"));
        cars.add(new Car("17", "Audi", "A4", 2020, 13440000, 35000, "Karaganda", "https://www.cars-data.com/pictures/audi/audi-a4_3402_22.jpg"));
        cars.add(new Car("18", "Volkswagen", "Golf", 2018, 6720000, 60000, "Almaty", "https://images.hgmsites.net/hug/2016-volkswagen-golf_100525757_h.jpg"));
        cars.add(new Car("19", "Ford", "Focus", 2019, 6000000, 52000, "Astana", "https://mediacloud.carbuyer.co.uk/image/private/s--q_n_7jeh--/v1579606975/carbuyer/focus-st_3.jpg"));
        cars.add(new Car("20", "Chevrolet", "Malibu", 2020, 7200000, 38000, "Shymkent", "https://www.motortrend.com/uploads/2023/08/2024-chevrolet-malibu-front-view-100.jpg"));
        cars.add(new Car("21", "Toyota", "Avalon", 2021, 9600000, 22000, "Almaty", "https://www.carscoops.com/wp-content/uploads/2021/12/2022-Toyota-Avalon-main.jpg"));
        cars.add(new Car("22", "Hyundai", "Santa Fe", 2020, 10560000, 32000, "Astana", "https://cdn.motor1.com/images/mgl/AE389/s1/hyundai-santa-fe-2-2-crdi-200-cv-4x4-at-style.jpg"));
        cars.add(new Car("23", "Kia", "Sorento", 2019, 11040000, 42000, "Shymkent", "https://www.kia.com/content/dam/kwcms/kme/global/en/assets/vehicles/sorento-pe-my25/discover/kia-sorento-my25-phev-34front-driving.jpg"));
        cars.add(new Car("24", "BMW", "X3", 2021, 14400000, 28000, "Atyrau", "https://images.hgmsites.net/hug/2015-bmw-x3-series_100456030_h.jpg"));
        cars.add(new Car("25", "Mercedes", "GLC", 2020, 15840000, 34000, "Karaganda", "https://all-auto.org/wp-content/uploads/2021/02/Mersedes-GLC-2.jpg"));
        cars.add(new Car("26", "Nissan", "Rogue", 2021, 9600000, 26000, "Almaty", "https://images.hgmsites.net/hug/2020-nissan-rogue_100722257_h.jpg"));
        cars.add(new Car("27", "Audi", "Q5", 2020, 12960000, 31000, "Astana", "https://media.ed.edmunds-media.com/audi/q5/2017/oem/2017_audi_q5_4dr-suv_20t-premium-plus-quattro_fq_oem_1_1280.jpg"));
        cars.add(new Car("28", "Volkswagen", "Tiguan", 2022, 11520000, 18000, "Shymkent", "https://cdn.motor1.com/images/mgl/VzMz6y/s1/vw-tiguan-2024.jpg"));
        cars.add(new Car("29", "Ford", "Escape", 2023, 10080000, 15000, "Almaty", "https://media.ed.edmunds-media.com/ford/escape/2024/fe/2024_ford_escape_actf34_fe_1213221_1280.jpg"));
        cars.add(new Car("30", "Chevrolet", "Equinox", 2021, 9120000, 29000, "Astana", "https://fotos.perfil.com/2024/01/23/chevrolet-equinox-1742945.jpg"));
    }

    private void initTestUsers() {
        User Ernar = new User("Ernar", "admin@gmail.com", "123");
        Ernar.addFavorite("1");
        Ernar.addFavorite("4");
        Ernar.addFavorite("7");
        Ernar.addFavorite("10");
        Ernar.addFavorite("14");
        Ernar.setBalance(1500000000.0);
        users.put("admin@gmail.com", Ernar);

        User testUser = new User("damir", "user@mail.com", "1234");
        testUser.addFavorite("2");
        testUser.addFavorite("5");
        testUser.addFavorite("8");
        testUser.addFavorite("15");
        testUser.setBalance(15000000.0);
        users.put("user@mail.com", testUser);

        users.put("test@gmail.com", new User("Тестовый", "test@gmail.com", "test"));
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }

    public Car getCarById(String id) {
        return cars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public boolean registerUser(String username, String email, String password) {
        if (users.containsKey(email)) return false;
        users.put(email, new User(username, email, password));
        return true;
    }

    public User login(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    public void logout() {
        currentUser = null;
    }
}