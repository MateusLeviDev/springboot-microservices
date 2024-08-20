CREATE SEQUENCE IF NOT EXISTS address_id_seq;

CREATE TABLE IF NOT EXISTS address (
    id bigint NOT NULL DEFAULT nextval('address_id_seq') PRIMARY KEY,
    zip_code VARCHAR(100) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(10) NOT NULL,
    complement VARCHAR(255),
    neighborhood VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS individual_client_id_seq;

CREATE TABLE IF NOT EXISTS individual_client(
  id bigint NOT NULL DEFAULT nextval('individual_client_id_seq') PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  document VARCHAR(100) NOT NULL,
  mothers_name VARCHAR(255) NOT NULL,
  is_deleted BOOLEAN,
  address_id BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE INDEX idx_individual_client_document ON individual_client(document);