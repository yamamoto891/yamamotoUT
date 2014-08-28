package unit.user.parts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import user.bean.RegistrantInfo;
import user.parts.RegInfDAO;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class RegInfDAOTest {

	DecimalFormat df = new DecimalFormat("000");
	
	final String DEF_1 = "001,鈴木太郎,35";
	final String DEF_2 = "002,Tommy,25";
	final String DEF_3 = "003,山田花子,30";
	
	List<RegistrantInfo> list = null;
	RegInfDAO dao = null;
	
	final String OK = "OK";
	final String NG = "NG";
	String result = NG;

	@BeforeClass
	public static void initJndi() throws Exception {
	    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
	    System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
	    InitialContext ic = new InitialContext();
	    ic.createSubcontext("java:");
	    ic.createSubcontext("java:comp");
	    ic.createSubcontext("java:comp/env");
	    ic.createSubcontext("java:comp/env/jdbc");
	    MysqlDataSource ds = new MysqlDataSource();
	    ds.setUser("root");
	    ds.setPassword("root");
	    ds.setURL("jdbc:mysql://localhost/Task");
	    ic.bind("java:comp/env/jdbc/Task", ds);
	}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
					
		dao = new RegInfDAO();
		list = dao.getReglist();
		for(RegistrantInfo info : list){
			dao.delete(info.getrId());
			
		}
		list = new ArrayList<RegistrantInfo>();
		result = NG;
		

		
	}

	@After
	public void tearDown() throws Exception {
		
		System.out.println("実施結果： " + result);
		System.out.println();
		
		dao.close();

	}

	@Test
	public void test002_001() {
		
		System.out.println("試験項目: UT002-001");
		
		dao.insert("001","鈴木太郎","35");
		dao.insert("002","Tommy","25");
		dao.insert("003","山田花子","30");
		
		
		//---------試験実施ここから------------
		dao.insert("004","佐藤路未央","28");
		
		list = dao.getReglist();
		String[] compare = {DEF_1,DEF_2,DEF_3,"004,佐藤路未央,28"};	
		if(list.size()!=compare.length){
			fail("DBレコード数不一致");
		}
		int i = 0 ;
		for(RegistrantInfo info : list){
			assertEquals(compare[i],info.getrId()+","+info.getrName()+","+info.getrAge());
			i++;
		}
		result = OK;

	}
	
	@Test
	public void test002_002() {
		
		System.out.println("試験項目: UT002-002");
		
		dao.insert("001","鈴木太郎","35");
		dao.insert("002","Tommy","25");
		dao.insert("003","山田花子","30");
		
		//---------試験実施ここから------------
		
		dao.update("002","Michael","29");
		
		list = dao.getReglist();
		
		String[] compare = {DEF_1,"002,Michael,29",DEF_3};	
		
		if(list.size()!=compare.length){
			fail("DBレコード数不一致");
		}
		int i = 0 ;
		for(RegistrantInfo info : list){
			
			assertEquals(compare[i],info.getrId()+","+info.getrName()+","+info.getrAge());
			i++;
		}
		result = OK;
			
	}
	
	@Test
	public void test002_003() {
		
		System.out.println("試験項目: UT002-003");
		
		dao.insert("001","鈴木太郎","35");
		dao.insert("002","Tommy","25");
		dao.insert("003","山田花子","30");
		
		//---------試験実施ここから------------
		
		dao.delete("001");
		
		list = dao.getReglist();
		
		String[] compare = {DEF_2,DEF_3};	
		
		if(list.size()!=compare.length){
			fail("DBレコード数不一致");
		}
		
		int i = 0 ;
		
		for(RegistrantInfo info : list){
			
			assertEquals(compare[i],info.getrId()+","+info.getrName()+","+info.getrAge());
			i++;
		}
		result = OK;
			
	}
	
	@Test
	public void test002_004() {
		
		System.out.println("試験項目: UT002-004");
		
		dao.insert("001","鈴木太郎","35");
		dao.insert("002","Tommy","25");
		dao.insert("003","山田花子","30");
		
		//---------試験実施ここから------------
		
		
		list = dao.getReglist();
		
		String[] compare = {DEF_1,DEF_2,DEF_3};	
		
		if(list.size()!=compare.length){
			fail("DBレコード数不一致");
		}
		
		int i = 0 ;
		
		for(RegistrantInfo info : list){
			
			assertEquals(compare[i],info.getrId()+","+info.getrName()+","+info.getrAge());
			i++;
		}
		result = OK;
			
	}
	@Test
	public void test002_005() {
		
		System.out.println("試験項目: UT002-005");

		
		//---------試験実施ここから------------
		
		
		assertEquals("001", dao.getNextId());
		
		result = OK;
			
	}
	
}
