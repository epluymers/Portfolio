--Elicia Pluymers
--Term Project
--SQL Code
--CSCI-N 311
/*****************************Package Header*********************************/
CREATE OR REPLACE PACKAGE pkg_term_proj
IS
  PROCEDURE p_login
  (p_username IN mm_login_table.username%TYPE, 
   p_password IN mm_login_table.password%TYPE, 
   p_login_type OUT mm_login_table.login_type%TYPE, 
   p_member_id OUT mm_login_table.member_id%TYPE);
    
  PROCEDURE p_add_movie
       (p_title IN mm_movie.movie_title%TYPE,
       p_category IN mm_movie.movie_title%TYPE,
       p_value IN mm_movie.movie_value%TYPE,
       p_quantity IN mm_movie.movie_qty%TYPE);
    
    PROCEDURE p_delete_movie
      (p_movieid IN mm_movie.movie_id%TYPE);
    
    PROCEDURE p_edit_movie
     (p_column IN VARCHAR2,
     p_string IN mm_movie.movie_title%TYPE,
     p_int IN NUMBER,
     p_movieid IN mm_movie.movie_id%TYPE);
    
    PROCEDURE p_add_member
       (p_first IN mm_member.first%TYPE,
       p_last IN mm_member.last%TYPE,
       p_license IN mm_member.license_no%TYPE,
       p_state IN mm_member.license_st%TYPE,
       p_card IN mm_member.credit_card%TYPE,
       p_mail IN mm_member.mailing_list%TYPE);
    
    PROCEDURE p_delete_member
      (p_memberid IN mm_member.member_id%TYPE);
    
    PROCEDURE p_edit_member
     (p_col IN VARCHAR2,
      p_input IN VARCHAR2,
      p_memberid IN NUMBER);
    
    PROCEDURE p_rent_movie
      (p_movieid IN mm_rental.movie_id%TYPE,
      p_memberid IN mm_rental.member_id%TYPE,
      p_paymethod IN mm_rental.payment_methods_id%TYPE);
    
    PROCEDURE p_movies_rented
      (p_memberid IN mm_rental.member_id%TYPE,
      p_rentnum OUT NUMBER);
    
    PROCEDURE p_movie_rentable
      (p_movieid IN mm_rental.movie_id%TYPE,
      p_rentable OUT NUMBER);
    
    PROCEDURE p_return_movie
     (p_movieid IN mm_rental.movie_id%TYPE,
      p_memberid IN mm_rental.member_id%TYPE,
      p_returnable OUT NUMBER);
    
    PROCEDURE p_check_overdue;
END;

/*****************************Package Body*********************************/
CREATE OR REPLACE PACKAGE BODY pkg_term_proj
IS
    PROCEDURE p_login
      (p_username IN mm_login_table.username%TYPE, 
       p_password IN mm_login_table.password%TYPE, 
       p_login_type OUT mm_login_table.login_type%TYPE, 
       p_member_id OUT mm_login_table.member_id%TYPE)
    IS
    BEGIN
      p_login_type := -1;
      p_member_id := -1;
      SELECT login_type, member_id INTO p_login_type, p_member_id
        FROM mm_login_table WHERE p_username = username AND p_password = password;
      IF p_member_id > 0 THEN p_check_overdue();
      END IF;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN dbms_output.put_line('Password Error');
    END p_login;
    
    PROCEDURE p_add_movie
       (p_title IN mm_movie.movie_title%TYPE,
       p_category IN mm_movie.movie_title%TYPE,
       p_value IN mm_movie.movie_value%TYPE,
       p_quantity IN mm_movie.movie_qty%TYPE)
    IS
    BEGIN
       INSERT INTO MM_MOVIE (movie_id, movie_title, movie_cat_id, movie_value, movie_qty) 
         VALUES(mm_movie_seq.nextval, p_title, p_category, p_value, p_quantity);
    END p_add_movie;
    
    PROCEDURE p_delete_movie
      (p_movieid IN mm_movie.movie_id%TYPE)
    IS
    BEGIN
      DELETE FROM MM_MOVIE WHERE MOVIE_ID = p_movieid;
    END p_delete_movie;
    
    PROCEDURE p_edit_movie
     (p_column IN VARCHAR2,
     p_string IN mm_movie.movie_title%TYPE,
     p_int IN NUMBER,
     p_movieid IN mm_movie.movie_id%TYPE)
    IS
      lv_query VARCHAR2(300);
    BEGIN
      IF p_column = 'MOVIE_TITLE' THEN
        lv_query := 'UPDATE MM_MOVIE SET ' || p_column || ' = :ph_string WHERE MOVIE_ID = ' || p_movieid;
        EXECUTE IMMEDIATE lv_query USING p_string;
      ELSE
        EXECUTE IMMEDIATE 'UPDATE MM_MOVIE SET ' || p_column || ' = ' || p_int || ' WHERE MOVIE_ID = ' || p_movieid;
      END IF;
    END p_edit_movie;
    
    PROCEDURE p_add_member
       (p_first IN mm_member.first%TYPE,
       p_last IN mm_member.last%TYPE,
       p_license IN mm_member.license_no%TYPE,
       p_state IN mm_member.license_st%TYPE,
       p_card IN mm_member.credit_card%TYPE,
       p_mail IN mm_member.mailing_list%TYPE)
    IS
    BEGIN
      INSERT INTO MM_MEMBER(member_id, first, last, license_no, license_st, credit_card, mailing_list)
      VALUES(mm_member_seq.nextval, p_first, p_last, p_license, p_state, p_card, p_mail);
    END p_add_member;
    
    PROCEDURE p_delete_member
      (p_memberid IN mm_member.member_id%TYPE)
    IS
    BEGIN
      DELETE FROM MM_MEMBER WHERE MEMBER_ID = p_memberid;
    END p_delete_member;
    
    PROCEDURE p_edit_member
     (p_col IN VARCHAR2,
      p_input IN VARCHAR2,
      p_memberid IN NUMBER)
    IS
      lv_query VARCHAR2(300);
    BEGIN
      lv_query := 'UPDATE MM_MEMBER SET ' || p_col || ' = :ph_input WHERE MEMBER_ID = ' || p_memberid;
      EXECUTE IMMEDIATE lv_query USING p_input;
    END p_edit_member;
    
    PROCEDURE p_rent_movie
      (p_movieid IN mm_rental.movie_id%TYPE,
      p_memberid IN mm_rental.member_id%TYPE,
      p_paymethod IN mm_rental.payment_methods_id%TYPE)
    IS
    BEGIN
      INSERT INTO MM_RENTAL(rental_id, member_id, movie_id, checkout_date, payment_methods_id)
      VALUES (mm_rental_seq.nextval, p_memberid, p_movieid, SYSDATE, p_paymethod);
    END p_rent_movie;
    
    PROCEDURE p_movies_rented
      (p_memberid IN mm_rental.member_id%TYPE,
      p_rentnum OUT NUMBER)
    IS
    BEGIN
      SELECT COUNT(rental_id) INTO p_rentnum FROM mm_rental WHERE checkin_date IS NULL and member_id = p_memberid;
    END p_movies_rented;
    
    PROCEDURE p_movie_rentable
      (p_movieid IN mm_rental.movie_id%TYPE,
      p_rentable OUT NUMBER)
    IS
      lv_qtyavail NUMBER(2,0) := 0;
    BEGIN
      SELECT movie_qty INTO lv_qtyavail FROM mm_movie WHERE movie_id = p_movieid;
      IF lv_qtyavail > 0 THEN p_rentable := 0;
      ELSE p_rentable := 1;
      END IF;
    END p_movie_rentable;
    
    PROCEDURE p_return_movie
     (p_movieid IN mm_rental.movie_id%TYPE,
      p_memberid IN mm_rental.member_id%TYPE,
      p_returnable OUT NUMBER)
    IS
      lv_rentalid NUMBER(2,0);
    BEGIN
      SELECT rental_id INTO lv_rentalid FROM mm_rental WHERE movie_id = p_movieid AND member_id = p_memberid AND checkin_date IS NULL FETCH FIRST 1 ROWS ONLY;
      UPDATE mm_rental
        SET checkin_date = SYSDATE
        WHERE rental_id = lv_rentalid;
      p_returnable := 0;
      
      p_check_overdue();
      EXCEPTION
      WHEN NO_DATA_FOUND THEN p_returnable := 1;
    END p_return_movie;
    
    PROCEDURE p_check_overdue
    IS
      CURSOR cur_overdue IS SELECT TRUNC(SYSDATE - CHECKOUT_DATE), MEMBER_ID 
        FROM MM_RENTAL WHERE CHECKIN_DATE IS NULL;
      TYPE t_rows IS RECORD (r_days NUMBER, r_id NUMBER);
      r_rows t_rows;
    BEGIN
        UPDATE MM_MEMBER
          SET SUSPENSION = 'N';
        OPEN cur_overdue;
        LOOP
           FETCH cur_overdue INTO r_rows;
           EXIT WHEN cur_overdue%NOTFOUND;
         IF r_rows.r_days > 5 THEN
            UPDATE MM_MEMBER
            SET SUSPENSION = 'Y'
            WHERE MEMBER_ID = r_rows.r_id;
         END IF;
        END LOOP;
    END p_check_overdue;
END;

/*****************************Trigger 1*********************************/
CREATE OR REPLACE TRIGGER tg_book_rent
       AFTER INSERT ON mm_rental
       FOR EACH ROW
    DECLARE
    BEGIN
      IF :NEW.checkin_date IS NULL THEN 
        UPDATE MM_MOVIE
          SET MOVIE_QTY = MOVIE_QTY - 1
          WHERE MOVIE_ID = :NEW.MOVIE_ID;
      END IF;
END;

/*****************************Trigger 2*********************************/
CREATE OR REPLACE TRIGGER tg_book_return
       AFTER UPDATE OF checkin_date ON mm_rental
       FOR EACH ROW
    DECLARE
    BEGIN
      IF :OLD.checkin_date IS NULL AND :NEW.checkin_date IS NOT NULL THEN 
        UPDATE MM_MOVIE
          SET MOVIE_QTY = MOVIE_QTY + 1
          WHERE MOVIE_ID = :OLD.MOVIE_ID;
      END IF;
END;

/*****************************Table and Sequence Creation*********************************/
CREATE SEQUENCE mm_login_table_seq  start with 3;
CREATE SEQUENCE mm_movie_seq  start with 13;
CREATE SEQUENCE mm_member_seq  start with 15;

CREATE TABLE mm_login_table
  (login_id NUMBER(4,0) PRIMARY KEY, 
   member_id   NUMBER(4,0), 
   username VARCHAR2(15), 
   password VARCHAR2(15), 
   login_type NUMBER(1));

INSERT INTO MM_MEMBER (member_id, last, first, license_no, license_st, credit_card, suspension, mailing_list) 
  VALUES (1, 'Doe', 'John', '123456789', 'VA', '112233445566', 'N', 'N');

INSERT INTO MM_LOGIN_TABLE (login_id, member_id, username, password, login_type) 
  VALUES (1, 10, 'jdoe', 'memberpass', 1);
INSERT INTO MM_LOGIN_TABLE (login_id, username, password, login_type) 
  VALUES (2, 'admin', 'adminpass', 0);