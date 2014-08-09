--kpi export
Select  TIME_ID,SVC_ID, PROC_ID, PROT_ID, MME_ID, APN_ID
        , sum(PROCEDUREATTEMPTS), sum(PROCEDUREFAILURES), sum(PROCEDUREFAILURES) / sum(PROCEDUREATTEMPTS), sum(PROCEDURELATENCY) / sum(PROCEDURELATENCYATT), sum(PROCEDURESUCCESS), sum(PROCEDURESUCCESS) / sum(PROCEDUREATTEMPTS)
From S1MMENAS_LPAMM_15_USER
Where TIME_ID >= TimeIDUtils.date_to_timeID(TO_DATE('2012/05/24 15:15:00', 'YYYY/MM/DD HH24:MI:SS'))
 And TIME_ID < TimeIDUtils.date_to_timeID(TO_DATE('2012/05/24 15:20:00', 'YYYY/MM/DD HH24:MI:SS'))
 And SVC_ID in (0)
 And PROT_ID||':'||PROC_ID like ('%710:14%')
 And PROT_ID in (710)
 And MME_ID in (-130)
 And APN_ID in (-400)
Group By  TIME_ID,SVC_ID, PROC_ID, PROT_ID, MME_ID, APN_ID
Having sum(PROCEDUREATTEMPTS)>0 And sum(PROCEDURELATENCYATT)>0

SELECT  dtd.name  tableName,
                dta.PARENT_ID tableId,
                dtd.resolution,
                dtd.distribution,
                dta.DIMENSION_NAME dimName,
                dta.KEY_ID keyId,
                dta.LOOKUP_TABLE_NAME lookupTableName,
                dta.LOOKUP_COLUMN_NAME lookupColumnName
            FROM CONF_DATA_TABLE_ATTRIBUTES dta,
                CONF_DATA_TABLE dtd
            where dta.PARENT_ID  = dtd.ID and dtd.name ='S1MMENAS_LPAMM_15_USER'
            order by 1
			
select ktd.DATA_TABLE_ID tableId,
                kf.FORMULA formula,
                kf.NAME formulaName,
                kd.DISPLAY_NAME kpiName,
                dtd.distribution distribution,
                ic.INTERFACE_TYPE_ID interfaceId
            FROM
                CONF_DATA_TABLE dtd,
                CONF_KPI kd,
                CONF_KPI_FORMULA kf,
                CONF_KPI_DATA_TABLES ktd,
                CONF_INTFTYP_CAT ic
            where
                kf.MEASURE_TYPE!='VOLUME'
                and dtd.id= ktd.data_table_id
                and kd.id = kf.kpi_id
                and ic.id = kd.INTF_TYPE_CATEGORY_ID
                and kd.id = ktd.KPI_ID and dtd.name = 'S1MMENAS_LPAMM_15_USER'
            order by 1, 2
   
--fmgen
SELECT dta.ID,
       upper(dta.LOOKUP_COLUMN_NAME) LOOKUP_COLUMN_NAME,
       dta.PARENT_ID,
       upper(dta.DIMENSION_NAME) DIMENSION_NAME,
       upper(dta.LOOKUP_TABLE_NAME) LOOKUP_TABLE_NAME,
       upper(dtd.NAME) NAME,
       upper(dta.TABLE_NAME_ALIAS) TABLE_NAME_ALIAS
  FROM CONF_DATA_TABLE_ATTRIBUTES dta
 INNER JOIN CONF_DATA_TABLE dtd
    ON dta.PARENT_ID = dtd.ID
 WHERE dtd.APP_ID = 1
   AND (dtd.EXPORT = 'ALL' OR dtd.EXPORT = 'COGNOS')
   AND dta.LOOKUP_TABLE_NAME <> 'null'
   AND dta.LOOKUP_COLUMN_NAME <> 'null'
   AND upper(dtd.NAME) like '%LR1%'

update (select *
          from Conf_Data_Table t1
         inner join CONF_DATA_TABLE_ATTRIBUTES t2
            on t1.id = t2.parent_id
         where t1.name like '%850%'
           and t2.dimension_name like 'Q850%')
   set table_name_alias = 'CONF_RESPONSE_CODE_1'

--get kpi query table
select a.name, a.display_name, c.name
  from (CONF_KPI a inner join CONF_KPI_DATA_TABLES b on a.id = b.kpi_id)
 inner join CONF_DATA_TABLE c
    on b.data_table_id = c.id
 where c.export <> 'COGNOS'
   and a.name = 'Aif_CC_Call_Setup_Procedure_Attempts'
