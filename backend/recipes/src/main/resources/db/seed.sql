BEGIN;

-- Sample user
INSERT INTO app_users (username, profile_picture_url, region, biography)
VALUES ('demo_chef', NULL, 'Novi Sad',
        'Demo profile used for testing the Recipes application.')
    ON CONFLICT (username) DO NOTHING;

-- Meal types
INSERT INTO meal_types (name)
VALUES ('Breakfast'), ('Lunch'), ('Dinner'), ('Snack')
    ON CONFLICT (name) DO NOTHING;

-- Dish types
INSERT INTO dish_types (name)
VALUES ('Soup'), ('Main Course'), ('Salad'), ('Dessert')
    ON CONFLICT (name) DO NOTHING;

-- Dietary types
INSERT INTO dietary_types (name)
VALUES ('Regular'), ('Vegetarian'), ('Vegan')
    ON CONFLICT (name) DO NOTHING;

-- Ingredient categories
INSERT INTO ingredient_categories (name)
VALUES ('Vegetables'), ('Fruits'), ('Meat'), ('Dairy'), ('Grains')
    ON CONFLICT (name) DO NOTHING;

-- Allergens
INSERT INTO allergens (name)
VALUES ('Milk'), ('Eggs'), ('Peanuts'), ('Wheat'), ('Soy')
    ON CONFLICT (name) DO NOTHING;


-- Recipe 1: Tomato Soup
INSERT INTO recipes (
    name, description, author_id, meal_type_id, dish_type_id,
    dietary_type_id, servings, preparation_time,
    main_photo_url, creation_date, origin_story
)
SELECT
    'Tomato Soup', 'Simple homemade tomato soup',
    u.id, mt.id, dt.id, diet.id,
    4, 30, NULL, CURRENT_TIMESTAMP, NULL
FROM app_users u
         JOIN meal_types mt ON mt.name = 'Lunch'
         JOIN dish_types dt ON dt.name = 'Soup'
         JOIN dietary_types diet ON diet.name = 'Vegan'
WHERE u.username = 'demo_chef'
  AND NOT EXISTS (
    SELECT 1 FROM recipes r
    WHERE r.name = 'Tomato Soup' AND r.author_id = u.id
);


-- Recipe 2: Vegetable Pasta
INSERT INTO recipes (
    name, description, author_id, meal_type_id, dish_type_id,
    dietary_type_id, servings, preparation_time,
    main_photo_url, creation_date, origin_story
)
SELECT
    'Vegetable Pasta', 'Pasta with fresh vegetables',
    u.id, mt.id, dt.id, diet.id,
    2, 25, NULL, CURRENT_TIMESTAMP, NULL
FROM app_users u
         JOIN meal_types mt ON mt.name = 'Dinner'
         JOIN dish_types dt ON dt.name = 'Main Course'
         JOIN dietary_types diet ON diet.name = 'Vegetarian'
WHERE u.username = 'demo_chef'
  AND NOT EXISTS (
    SELECT 1 FROM recipes r
    WHERE r.name = 'Vegetable Pasta' AND r.author_id = u.id
);


-- Recipe 3: Chicken and Rice
INSERT INTO recipes (
    name, description, author_id, meal_type_id, dish_type_id,
    dietary_type_id, servings, preparation_time,
    main_photo_url, creation_date, origin_story
)
SELECT
    'Chicken and Rice', 'Chicken served with cooked rice',
    u.id, mt.id, dt.id, diet.id,
    3, 45, NULL, CURRENT_TIMESTAMP, NULL
FROM app_users u
         JOIN meal_types mt ON mt.name = 'Lunch'
         JOIN dish_types dt ON dt.name = 'Main Course'
         JOIN dietary_types diet ON diet.name = 'Regular'
WHERE u.username = 'demo_chef'
  AND NOT EXISTS (
    SELECT 1 FROM recipes r
    WHERE r.name = 'Chicken and Rice' AND r.author_id = u.id
);


-- Recipe 4: Fruit Salad
INSERT INTO recipes (
    name, description, author_id, meal_type_id, dish_type_id,
    dietary_type_id, servings, preparation_time,
    main_photo_url, creation_date, origin_story
)
SELECT
    'Fruit Salad', 'Fresh mixed seasonal fruits',
    u.id, mt.id, dt.id, diet.id,
    2, 10, NULL, CURRENT_TIMESTAMP, NULL
FROM app_users u
         JOIN meal_types mt ON mt.name = 'Snack'
         JOIN dish_types dt ON dt.name = 'Salad'
         JOIN dietary_types diet ON diet.name = 'Vegan'
WHERE u.username = 'demo_chef'
  AND NOT EXISTS (
    SELECT 1 FROM recipes r
    WHERE r.name = 'Fruit Salad' AND r.author_id = u.id
);


-- Recipe 5: Banana Pancakes
INSERT INTO recipes (
    name, description, author_id, meal_type_id, dish_type_id,
    dietary_type_id, servings, preparation_time,
    main_photo_url, creation_date, origin_story
)
SELECT
    'Banana Pancakes', 'Homemade pancakes with bananas',
    u.id, mt.id, dt.id, diet.id,
    4, 20, NULL, CURRENT_TIMESTAMP, NULL
FROM app_users u
         JOIN meal_types mt ON mt.name = 'Breakfast'
         JOIN dish_types dt ON dt.name = 'Dessert'
         JOIN dietary_types diet ON diet.name = 'Vegetarian'
WHERE u.username = 'demo_chef'
  AND NOT EXISTS (
    SELECT 1 FROM recipes r
    WHERE r.name = 'Banana Pancakes' AND r.author_id = u.id
);

COMMIT;