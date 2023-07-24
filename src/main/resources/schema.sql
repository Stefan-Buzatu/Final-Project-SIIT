CREATE TABLE IF NOT EXISTS client
(
    client_id uuid NOT NULL,
    client_first_name character varying(20) NOT NULL,
    client_last_name character varying(20) NOT NULL,
    client_email character varying(25) NOT NULL,
    client_phone_number character varying(15) NOT NULL,
    CONSTRAINT client_pkey PRIMARY KEY (client_id)
    );

CREATE TABLE IF NOT EXISTS entry
(
    client_id uuid NOT NULL,
    date date NOT NULL,
    CONSTRAINT fk_cl_en
    FOREIGN KEY (client_id)
    REFERENCES client(client_id)
    ON DELETE CASCADE
    );
CREATE TABLE IF NOT EXISTS reward
(
    reward_id uuid NOT NULL,
    reward character varying(50) NOT NULL,
    CONSTRAINT reward_pkey PRIMARY KEY (reward_id)
    );


INSERT INTO reward(reward_id, reward)
VALUES ('e36ca10b-d72f-4f46-8d1e-62747a5cbd87', 'Americano');

INSERT INTO reward(reward_id, reward)
VALUES ('df2ce77a-3047-4c60-a5ee-f35072714f33', 'Vanilla cake');

INSERT INTO reward(reward_id, reward)
VALUES ('3e79239f-a12d-4204-99cc-275c9bc54bc4', 'Chocolate cake');

INSERT INTO reward(reward_id, reward)
VALUES ('a38d97b4-8769-490a-a650-49e5a566e085', 'Chocolate ice cream');

INSERT INTO reward(reward_id, reward)
VALUES ('07a21b48-1920-4b23-a42f-07709f5e0e8a', 'Vanilla ice cream');

INSERT INTO reward(reward_id, reward)
VALUES ('64d2a5e6-d4ce-4a94-8b6e-2f9a3f80ee5c', 'Black Coffee');

INSERT INTO reward(reward_id, reward)
VALUES ('43c0241d-1ef9-47b1-970a-594350c15d44', 'Cappuccino');

INSERT INTO reward(reward_id, reward)
VALUES ('d4776677-57eb-4bc4-a066-db064dab3a58', 'Latte');

INSERT INTO reward(reward_id, reward)
VALUES ('b8a3f2ad-d798-410e-8667-fc904d15105a', 'Espresso');

INSERT INTO reward(reward_id, reward)
VALUES ('32c2a155-88c0-41e4-8b57-1b9ffab1f4c1', 'Croissant');

UPDATE rewards SET no_entries='3' where reward_id='e36ca10b-d72f-4f46-8d1e-62747a5cbd87';
UPDATE rewards SET no_entries='4' where reward_id='df2ce77a-3047-4c60-a5ee-f35072714f33';
UPDATE rewards SET no_entries='5' where reward_id='3e79239f-a12d-4204-99cc-275c9bc54bc4';
UPDATE rewards SET no_entries='2' where reward_id='a38d97b4-8769-490a-a650-49e5a566e085';
UPDATE rewards SET no_entries='3' where reward_id='07a21b48-1920-4b23-a42f-07709f5e0e8a';
UPDATE rewards SET no_entries='2' where reward_id='64d2a5e6-d4ce-4a94-8b6e-2f9a3f80ee5c';
UPDATE rewards SET no_entries='4' where reward_id='43c0241d-1ef9-47b1-970a-594350c15d44';
UPDATE rewards SET no_entries='5' where reward_id='d4776677-57eb-4bc4-a066-db064dab3a58';
UPDATE rewards SET no_entries='5' where reward_id='b8a3f2ad-d798-410e-8667-fc904d15105a';
UPDATE rewards SET no_entries='3' where reward_id='32c2a155-88c0-41e4-8b57-1b9ffab1f4c1';
