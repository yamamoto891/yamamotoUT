package unit.user.parts;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import user.parts.RegInfCheck;


public class RegInfCheckTest  extends TestCase{

	final String OK = "OK";
	final String NG = "NG";
	
	String result = NG;
	
	RegInfCheck bean = null;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		result = NG;
		bean = new RegInfCheck();
		System.out.println("試験項目: "+ getName());
	}

	@After
	public void tearDown() throws Exception {
		
		System.out.println("実施結果： " + result);
		System.out.println();
	}

	@Test
	public void test001_001() {
		
		bean.checkName("0123456789");
		assertEquals(bean.getErrMsg(), "");
		result = OK;
		
	}
	
	@Test
	public void test001_002() {
		
		bean.checkName("あいうえおかきくけこ");
		assertEquals(bean.getErrMsg(), "");
		result = OK;
		
	}
	
	@Test
	public void test001_003() {
		
		bean.checkName("01234567890");
		assertEquals(bean.getErrMsg(), "名前は10桁以内で入力してください。<br />");
		result = OK;
		
	}
	
	@Test
	public void test001_004() {
		
		bean.checkName("あいうえおかきくけこさ");
		assertEquals(bean.getErrMsg(), "名前は10桁以内で入力してください。<br />");
		result = OK;
		
	}
	
	@Test
	public void test001_005() {
		
		bean.checkAge("16");
		assertEquals(bean.getErrMsg(), "");
		result = OK;
		
	}
	
	@Test
	public void test001_006() {
		
		bean.checkAge("60");
		assertEquals(bean.getErrMsg(), "");
		result = OK;
		
	}
	
	@Test
	public void test001_007() {
		
		bean.checkAge("15");
		assertEquals(bean.getErrMsg(), "年齢は(16-60)の範囲で入力してください。<br />");
		result = OK;
		
	}
	
	@Test
	public void test001_008() {
		
		bean.checkAge("61");
		assertEquals(bean.getErrMsg(), "年齢は(16-60)の範囲で入力してください。<br />");
		result = OK;
		
	}

	@Test
	public void test001_009() {
		
		bean.checkAge("１６");
		
		assertEquals(bean.getErrMsg(), "年齢は数値(半角)で入力してください。<br />");
		result = OK;
		
	}
	
	@Test
	public void test001_010() {
		
		bean.checkAge("あいうえお");
		assertEquals(bean.getErrMsg(), "年齢は数値(半角)で入力してください。<br />"
				+ "年齢は(16-60)の範囲で入力してください。<br />");
		result = OK;
		
	}
	
	@Test
	public void test001_011() {
		
		bean.checkAge("16あいうえお");
		assertEquals(bean.getErrMsg(), "年齢は数値(半角)で入力してください。<br />"
				+ "年齢は(16-60)の範囲で入力してください。<br />");
		result = OK;
		
	}
	
	@Test
	public void test001_012() {
		
		bean.checkId("999");
		assertEquals(bean.getErrMsg(), "");
		result = OK;
		
	}
	
	@Test
	public void test001_013() {
		
		bean.checkId("1000");
		assertEquals(bean.getErrMsg(), "登録可能なID（999）を超えています。管理者に問い合わせてください。<br />");
		result = OK;
		
		
		
	}
}
