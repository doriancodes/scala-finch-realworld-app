CREATE TABLE users (
  id UUID default uuid_generate_v4() PRIMARY KEY,
  username TEXT NOT NULL,
  email TEXT NOT NULL,
  bio TEXT,
  image TEXT,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  CONSTRAINT user_email_unique UNIQUE (email),
  CONSTRAINT user_username_unique UNIQUE (username)
);

CREATE TABLE auth_users (
  id UUID default uuid_generate_v4() PRIMARY KEY,
  email TEXT NOT NULL,
  password TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  CONSTRAINT security_user_email_unique UNIQUE (email)
);