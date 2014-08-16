--http://www.oracle.com/pls/db112/homepage
select * from test t
where t.HOBBY = 'basketball'
and exists (select 1 from test t1 where t.name=t1.name and t1.HOBBY  = 'badminton');
select t1.name from (select * from t where t.HOBBY = 'basketball') t1 inner join (select * from t where t.HOBBY = 'basketball') t2 on t1.name=t2.name

--get table column number
select table_name, column_name, column_id from user_tab_columns t where t.TABLE_NAME='IUCSCALL_MPR7_15_USER'

--try to update a view

/* column names*/
select max(substr(SYS_CONNECT_BY_PATH(COLUMN_NAME, ','),2)) col from ( 
select COLUMN_NAME,column_id from user_tab_columns where table_name='VOICETDR_VPSD_HOUR_USER') 
start with column_id=1 
connect by column_id=rownum; 

select listagg(COLUMN_NAME, ',') within group (order by column_id) columns from user_tab_cols t where t.TABLE_NAME='EMP'

--flashback
Flashback database to timestamp to_timestamp('09-10-14 14:37:05','yy-mm-dd hh24:mi:ss');
drop table a;
flashback table a to before drop rename to B;
delete from A;
select * from A as of timestamp sysdate-5/1440;
select * from A as of timestamp to_timestamp('2009-10-15 19:04:16','YYYY-MM-DD hh24:mi:ss');
Insert into A select * from A as of timestamp to_timestamp('2009-10-15 19:04:16','YYYY-MM-DD hh24:mi:ss');
SELECT CURRENT_SCN FROM V$DATABASE;
select * from A as of scn 1095782;
--merge
merge into emp
using (select * from emp as of timestamp sysdate - 5 / 1440) t
on (emp.empno = t.empno)
when matched then
  update set emp.deptno = t.deptno;
when not matched then
  insert (empno) values (t.empno)
--plan_table
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);
--regexp
select ename,REGEXP_REPLACE(ename, 'S(\w+)', '\1s') AS "names after regexp" from emp where REGEXP_LIKE (ename, '^S');
--analytic function
select emp.empno,emp.ename,emp.job, emp.sal, count(*) over (PARTITION by job) count, rank() over (order by sal) rank from emp order by ename;
