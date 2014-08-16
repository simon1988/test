declare
  cursor c_job is
    select empno, ename, job, sal from emp where job = 'MANAGER';
  c_row c_job%rowtype;
begin
  --for
  for c_row in c_job loop
    dbms_output.put_line(c_row.empno || '-' || c_row.ename || '-' ||
                         c_row.job || '-' || c_row.sal);
  end loop;
  --fectch
  open c_job;
  loop
    fetch c_job
      into c_row;
    exit when c_job%notfound;
    dbms_output.put_line(c_row.empno || '-' || c_row.ename || '-' ||
                         c_row.job || '-' || c_row.sal);
  end loop;
  close c_job;
end;
/

declare
      cursor
          csr_AddComm(p_job nvarchar2)
      is
          select * from emp where   JOB=p_job FOR UPDATE OF COMM;
      r_AddComm  emp%rowtype;
      commInfo emp.comm%type;
begin
    for r_AddComm in csr_AddComm('SALESMAN') LOOP
        commInfo:=r_AddComm.COMM+500;
         UPDATE EMP SET COMM=commInfo where CURRENT OF csr_AddComm;
    END LOOP;
END;
/

declare
  cursor c_t is
    select * from t order by x desc;
  r_t   c_t%rowtype;
  n_num number := 2;
begin
  open c_t;
  while n_num > 0 loop
    fetch c_t
      into r_t;
    update t set y = 2 where x = r_t.x;
    n_num := n_num - 1;
  end loop;
  close c_t;
  commit;
end;
/
--random a value...akward.
declare
      cursor
          csr_random(p_time integer)
      is
          select * from GTPMM_SDPX_15_USER where time_id>=p_time FOR UPDATE OF COUNTER;
      r_eachrow  GTPMM_SDPX_15_USER%rowtype;
      updateColomn GTPMM_SDPX_15_USER.COUNTER%type;
begin
    for r_eachrow in csr_random(6746811) LOOP
        updateColomn:=round(dbms_random.value(10, 30));
         UPDATE GTPMM_SDPX_15_USER SET COUNTER=updateColomn where CURRENT OF csr_random;
    END LOOP;
END;
/