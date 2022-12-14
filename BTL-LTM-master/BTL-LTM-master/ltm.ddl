CREATE TABLE `User` (ID int(10) NOT NULL AUTO_INCREMENT, Username varchar(255), Password varchar(255), DisplayName varchar(255), PRIMARY KEY (ID));
CREATE TABLE Room (ID int(10) NOT NULL AUTO_INCREMENT, Description varchar(255), PRIMARY KEY (ID));
CREATE TABLE Message (ID int(10) NOT NULL AUTO_INCREMENT, UserID int(10) NOT NULL, RoomID int(10) NOT NULL, Content varchar(255), SendTime date, PRIMARY KEY (ID));
CREATE TABLE UserRoom (ID int(10) NOT NULL AUTO_INCREMENT, RoomID int(10) NOT NULL, UserID int(10) NOT NULL, PRIMARY KEY (ID));
ALTER TABLE UserRoom ADD CONSTRAINT FKUserRoom407341 FOREIGN KEY (UserID) REFERENCES `User` (ID);
ALTER TABLE UserRoom ADD CONSTRAINT FKUserRoom788774 FOREIGN KEY (RoomID) REFERENCES Room (ID);
ALTER TABLE Message ADD CONSTRAINT FKMessage682335 FOREIGN KEY (RoomID) REFERENCES Room (ID);
ALTER TABLE Message ADD CONSTRAINT FKMessage63769 FOREIGN KEY (UserID) REFERENCES `User` (ID);
