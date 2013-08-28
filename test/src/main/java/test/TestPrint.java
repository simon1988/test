package test;


public class TestPrint {

	static final String sql = "select c.gfcid as gfcid, customer_name as name, control_unit_cd as credit_control_unit, controlunit.unit_long_desc as control_unit_long_desc "
			+ ", independent_risk_unit_cd as independent_risk_unit, iru.unit_long_desc as iru_long_description, cr.rca_dt as rca_date, rating.risk_rating_cd as orr, "
			+ "c.domicile_country_cd as country_domicile, manager.choice_long_desc as responsible_officer, cr.source_of_baseline_orr_cd as baseline_orr_src_id, "
			+ "industry.level_2name as obl_risk_industry_l2_desc, cre.review_extension_dt as review_extension_date, cr.classification  "
			+ "from v_cus_customer c, v_cus_risk_attribute cr, (select distinct customer_id, review_extension_dt from v_cus_review_extension) cre, v_unit iru, v_unit controlunit, v_cus_confidential_rating rating, v_ref_choice manager, v_cus_manager manager2, v_cus_industry_classification class, ref_risk_mgmt_industry industry  "
			+ "where c.customer_id=cr.customer_id(+) and cr.independent_risk_unit_cd = iru.unit_cd(+) and c.customer_id=cre.customer_id(+) "
			+ "and control_unit_cd = controlunit.unit_cd(+) and c.customer_id=rating.customer_id(+) and manager2.manager_geid=manager.choice_cd(+) and manager.ref_choice_grp_cd(+)='MANAGER_GEID' and c.customer_id=manager2.customer_id(+) and manager2.manager_type_cd(+)=3 and c.customer_id=class.customer_id(+) and class.classification_cd=industry.level_4code(+) and class.classification_type(+) = 'CREDRISKOB' and c.gfcid in (:gfcIds)";


	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(sql);
	}
}
