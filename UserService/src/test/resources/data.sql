INSERT INTO public.usr_user(
    birth_date, latitude, longitude, created_at, id, updated_at, turkish_republic_id_number, username, first_name, last_name, status)
VALUES ('1990-01-01' ,NULL, NULL, '2021-01-01 00:00:00', 100, NULL, '12345678901', 'user1', 'User', 'One', 'ACTIVE');

INSERT INTO public.usr_user(
    birth_date, latitude, longitude, created_at, id, updated_at, turkish_republic_id_number, username, first_name, last_name, status)
VALUES ('1990-01-01' ,NULL, NULL, '2021-01-01 00:00:00', 200, NULL, '12345678902', 'user2', 'User', 'Two', 'ACTIVE');

INSERT INTO public.usr_user(
    birth_date, latitude, longitude, created_at, id, updated_at, turkish_republic_id_number, username, first_name, last_name, status)
VALUES ('1990-01-01' ,NULL, NULL, '2021-01-01 00:00:00', 300, NULL, '12345678903', 'user3', 'User','Three', 'ACTIVE');


INSERT INTO public.usr_user(
    birth_date, latitude, longitude, created_at, id, updated_at, turkish_republic_id_number, username, first_name, last_name, status)
VALUES ('1990-01-01' ,NULL, NULL, '2021-01-01 00:00:00', 400, NULL, '12345678904', 'user4', 'User', 'Four', 'ACTIVE');

INSERT INTO public.usr_user(
    birth_date, latitude, longitude, created_at, id, updated_at, turkish_republic_id_number, username, first_name, last_name, status)
VALUES ('1990-01-01' ,NULL, NULL, '2021-01-01 00:00:00', 500, NULL, '12345678905', 'user5', 'User', 'Five', 'PASSIVE');


INSERT INTO public.review(
    created_at, id, updated_at, user_id, review, rate, restaurant_id)
VALUES ('2021-01-01 00:00:00', 1000, NULL, 100, 'Review 1', 'ONE', 'f50d76b2-f2cd-4ee9-88ff-d252453b632f');

INSERT INTO public.review(
    created_at, id, updated_at, user_id, review, rate, restaurant_id)
VALUES ('2021-01-01 00:00:00', 2000, NULL, 200, 'Review 2', 'TWO', 'f50d76b2-f2cd-4ee9-88ff-d252453b632f');

INSERT INTO public.review(
    created_at, id, updated_at, user_id, review, rate, restaurant_id)
VALUES ('2021-01-01 00:00:00', 3000, NULL, 300, 'Review 3', 'THREE', 'f50d76b2-f2cd-4ee9-88ff-d252453b632f');

INSERT INTO public.review(
    created_at, id, updated_at, user_id, review, rate, restaurant_id)
VALUES ('2021-01-01 00:00:00', 4000, NULL, 400, 'Review 4', 'FOUR', 'f50d76b2-f2cd-4ee9-88ff-d252453b632f');

INSERT INTO public.review(
    created_at, id, updated_at, user_id, review, rate, restaurant_id)
VALUES ('2021-01-01 00:00:00', 5000, NULL, 500, 'Review 5', 'FIVE', 'f50d76b2-f2cd-4ee9-88ff-d252453b632f');