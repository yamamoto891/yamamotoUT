package unit.employ;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


public class OutLogTest extends TestCase{

	final String OK = "OK";
	final String NG = "NG";
	
	final String reg = "^[0-9]{4}?/[0-9]{2}?/[0-9]{2}? [0-9]{2}?:[0-9]{2}?:[0-9]{2}?:";
	
	final Pattern pattern = Pattern.compile(reg);
	
	String result = NG;
	
	File file = null;
	
	employ.OutLog bean = null;
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		file = new File("C:/test/log/log.txt");	
		FileWriter fw = new FileWriter(file);
		fw.write("");
		fw.close();
		
		System.out.println("試験項目: "+getName());
		
		result = NG;
	}

	@After
	public void tearDown() throws Exception {
		
		
		System.out.println("実施結果： " + result);
		System.out.println();
	}

	@Test
	public void test003_001() {
		
		bean = new employ.OutLog();

		bean.outLogDmp("sample：サンプル");
		
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(file);
			br= new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null){	
				Matcher match = pattern.matcher(line);
				System.out.println(line);
				assertTrue(match.find());
				assertTrue(line.endsWith("sample：サンプル"));
			  }
		
			result = OK;
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail();
			}
		
		}
			
	}

	@Test
	public void test003_002() {
		
		bean = new employ.OutLog();

		bean.outLogDmp(12345);
		
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(file);
			br= new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null){	
				Matcher match = pattern.matcher(line);
				System.out.println(line);
				assertTrue(match.find());
				assertTrue(line.endsWith("12345"));
			  }
		
			result = OK;
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail();
			}
		
		}
			
	}

}
