CREATE TABLE IF NOT EXISTS users (
    id SERIAL NOT NULL PRIMARY KEY,
    email TEXT NOT NULL,
    token TEXT NOT NULL,
    username TEXT NOT NULL,
    bio TEXT,
    image TEXT
);