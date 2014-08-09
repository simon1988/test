declare
i number;
n number;
begin
  i:=1;
  dbms_output.put_line('zhu');
  loop
    exit when i >10;
    begin
      select i*2+1 into n from dual;
      insert into t values (i,n);
      i:=i+1;
    end;
  end loop;
  commit;
end;
/  
  
