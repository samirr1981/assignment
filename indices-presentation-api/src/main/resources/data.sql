DROP TABLE IF EXISTS INSTRUMENT_TICKS_DETAILS;

CREATE TABLE INSTRUMENT_TICKS_DETAILS(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  instrument VARCHAR(50) NOT NULL,
  price DECIMAL(20, 2) NOT NULL,
  instrument_timestamp TIMESTAMP NOT NULL
);
