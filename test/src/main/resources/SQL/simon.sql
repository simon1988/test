create tablespace simon_tbs datafile '/export/oracle/simon/simon_tbs.dbf' size 5m;
create user simon identified by niu default tablespace simon_tbs;
GRANT
unlimited tablespace,
CREATE SESSION, CREATE ANY TABLE, CREATE ANY VIEW ,CREATE ANY INDEX, CREATE ANY PROCEDURE,
ALTER ANY TABLE, ALTER ANY PROCEDURE,
DROP ANY TABLE, DROP ANY VIEW, DROP ANY INDEX, DROP ANY PROCEDURE,
SELECT ANY TABLE, INSERT ANY TABLE, UPDATE ANY TABLE, DELETE ANY TABLE
TO simon;
