CREATE TABLE
    person
    (
        id INT NOT NULL AUTO_INCREMENT,
        lastName VARCHAR(255),
        email VARCHAR(255),
        birth DATETIME,
        PRIMARY KEY (id)
    )
    ENGINE=MyISAM DEFAULT CHARSET=utf8;