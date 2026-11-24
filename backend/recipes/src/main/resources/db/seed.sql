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
    4, 30, '/recipe_photos/tomato_soup.jpg',
    TIMESTAMP '2026-08-10 12:00:00', NULL
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
    2, 25, '/recipe_photos/vegetable_pasta.jpg',
    TIMESTAMP '2026-08-30 12:00:00', NULL
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
    3, 45, '/recipe_photos/chicken_rice.jpg',
    TIMESTAMP '2026-07-25 12:00:00', NULL
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
    2, 10, '/recipe_photos/fruit_salad.jpg',
    TIMESTAMP '2026-09-04 12:00:00', NULL
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
    4, 20, '/recipe_photos/banana_pancakes.jpg',
    TIMESTAMP '2026-09-15 12:00:00', NULL
FROM app_users u
JOIN meal_types mt ON mt.name = 'Breakfast'
JOIN dish_types dt ON dt.name = 'Dessert'
JOIN dietary_types diet ON diet.name = 'Vegetarian'
WHERE u.username = 'demo_chef'
  AND NOT EXISTS (
      SELECT 1 FROM recipes r
      WHERE r.name = 'Banana Pancakes' AND r.author_id = u.id
  );

-- Demo reviewers for the Popular feed
INSERT INTO app_users (username, region)
VALUES ('demo_reviewer_one', 'Novi Sad'),
       ('demo_reviewer_two', 'Novi Sad')
ON CONFLICT (username) DO NOTHING;

-- Recent ratings determine the ranking. Older ratings exercise the cutoff.
INSERT INTO ratings (recipe_id, user_id, rating_value, created_at, updated_at)
SELECT recipe.id,
       reviewer.id,
       sample.score,
       LOCALTIMESTAMP - sample.days_ago * INTERVAL '1 day',
       LOCALTIMESTAMP - sample.days_ago * INTERVAL '1 day'
FROM (VALUES
    ('Banana Pancakes', 'demo_reviewer_one', 3, 7),
    ('Banana Pancakes', 'demo_reviewer_two', 5, 45),
    ('Vegetable Pasta', 'demo_reviewer_one', 3, 14),
    ('Vegetable Pasta', 'demo_reviewer_two', 4, 3),
    ('Tomato Soup', 'demo_reviewer_one', 5, 45)
) AS sample(recipe_name, reviewer_name, score, days_ago)
JOIN app_users reviewer ON reviewer.username = sample.reviewer_name
JOIN recipes recipe ON recipe.name = sample.recipe_name
JOIN app_users author ON author.id = recipe.author_id
WHERE author.username = 'demo_chef'
ON CONFLICT (recipe_id, user_id) DO NOTHING;


UPDATE recipes r
SET main_photo_url = CASE r.name
                         WHEN 'Tomato Soup' THEN '/recipe_photos/tomato_soup.jpg'
                         WHEN 'Vegetable Pasta' THEN '/recipe_photos/vegetable_pasta.jpg'
                         WHEN 'Chicken and Rice' THEN '/recipe_photos/chicken_rice.jpg'
                         WHEN 'Fruit Salad' THEN '/recipe_photos/fruit_salad.jpg'
                         WHEN 'Banana Pancakes' THEN '/recipe_photos/banana_pancakes.jpg'
    END
    FROM app_users u
WHERE r.author_id = u.id
  AND u.username = 'demo_chef'
  AND r.main_photo_url IS NULL
  AND r.name IN (
    'Tomato Soup',
    'Vegetable Pasta',
    'Chicken and Rice',
    'Fruit Salad',
    'Banana Pancakes'
    );



COMMIT;