
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	username varchar(50) NOT NULL UNIQUE,
	email varchar(100) NOT NULL UNIQUE,
	passwd varchar(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS tasks (
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	title varchar(255) NOT NULL,
	status varchar(20) NOT NULL DEFAULT 'TODO',
	user_id UUID,
	CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);