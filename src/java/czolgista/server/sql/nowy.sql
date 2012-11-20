SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `czolgista` DEFAULT CHARACTER SET utf8 ;
USE `czolgista` ;

-- -----------------------------------------------------
-- Table `czolgista`.`groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `czolgista`.`groups` ;

CREATE  TABLE IF NOT EXISTS `czolgista`.`groups` (
  `groupid` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `groupname` VARCHAR(255) NULL ,
  `groupdesc` VARCHAR(255) NULL ,
  PRIMARY KEY (`groupid`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `czolgista`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `czolgista`.`user` ;

CREATE  TABLE IF NOT EXISTS `czolgista`.`user` (
  `userid` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(255) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `firstname` VARCHAR(255) NOT NULL ,
  `lastname` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`userid`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `czolgista`.`record`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `czolgista`.`record` ;

CREATE  TABLE IF NOT EXISTS `czolgista`.`record` (
  `recordid` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `date` TIMESTAMP NOT NULL ,
  `score` INT(11) NOT NULL ,
  `user_userid` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`recordid`) ,
  INDEX `fk_record_user_idx` (`user_userid` ASC) ,
  CONSTRAINT `fk_record_user`
    FOREIGN KEY (`user_userid` )
    REFERENCES `czolgista`.`user` (`userid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `czolgista`.`user_has_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `czolgista`.`user_has_groups` ;

CREATE  TABLE IF NOT EXISTS `czolgista`.`user_has_groups` (
  `user_userid` SMALLINT UNSIGNED NOT NULL ,
  `groups_groupid` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`user_userid`, `groups_groupid`) ,
  INDEX `fk_user_has_groups_groups1_idx` (`groups_groupid` ASC) ,
  CONSTRAINT `fk_user_has_groups_user1`
    FOREIGN KEY (`user_userid` )
    REFERENCES `czolgista`.`user` (`userid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_groups_groups1`
    FOREIGN KEY (`groups_groupid` )
    REFERENCES `czolgista`.`groups` (`groupid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
