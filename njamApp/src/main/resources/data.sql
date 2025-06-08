INSERT INTO restaurants (name, address, telephone_number, email, working_hours, currently_open, average_delivery_time, average_rating, max_number_of_orders, michelin_star, description)
VALUES
    ('Pasta House', 'Main Street 1', 1234567890, 'pasta@house.com', 10, TRUE, 30, 4.5, 100.0, 1, 'Authentic Italian restaurant.'),
    ('Burger World', 'Burger Alley 5', 9876543210, 'burger@world.com', 12, TRUE, 25, 4.2, 80.0, 0, 'Best burgers in town.'),
    ('Sushi Spot', 'Ocean Drive 3', 555666777, 'sushi@spot.com', 8, FALSE, 40, 4.8, 60.0, 2, 'Fresh sushi with a sea view.');

INSERT INTO reviews (restaurant_id, title, description, grade, date) VALUES
                                                                         (1, 'Odličan restoran', 'Hrana je bila izvrsna, sve preporuke!', 1, '2025-05-01'),
                                                                         (2, 'Solidno iskustvo', 'Ambijent je dobar, ali usluga osrednja.', 3, '2025-05-02'),
                                                                         (3, 'Nije loše', 'Cijene su previsoke za ono što se dobije.', 5, '2025-05-03'),
                                                                         (1, 'Loša usluga', 'Konobar je bio neljubazan, dugo smo čekali.', 1, '2025-05-08'),
                                                                         (2, 'Izvanredno', 'Savršeno mjesto za večeru s prijateljima!', 4, '2025-05-09'),
                                                                         (3, 'Ne bih preporučio', 'Hrana je bila hladna, a prostor prljav.', 5, '2025-05-10');