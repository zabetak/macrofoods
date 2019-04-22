package org.macrofoods.backend.servlets.dsl;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

	public static DslNode parse(String query) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(query.getBytes());
		return new DslParser(bais).root();
	}

	public void testEmptyRoot() throws Exception {
		String dsl = "root";
		assertEquals(dsl, parse(dsl).toString());
	}

	public void testOneLevelUnderRoot() throws Exception {
		String dsl = "root(child1, child2, child3)";
		assertEquals(dsl, parse(dsl).toString());
	}

	public void testOneLevelUnderRootWithSlash() throws Exception {
		String dsl = "root/child1";
		assertEquals("root(child1)", parse(dsl).toString());
	}

	public void testOneLevelUnderRootWithSlashAllowsOnlyOneChild() throws Exception {
		try {
			String dsl = "root/child1, child2";
			assertEquals("root(child1)", parse(dsl).toString());
		} catch (Exception e) {

		}
	}

	public void testComplexQueryWithMultipleNestingAndPredicates() throws Exception {
		//@formatter:off
		String dsl = 
				"root(\n" + 
				"  id,title,summary,conclusion,ingGroups(\n" + 
				"    name,ingredients\n" + 
				"    (\n" + 
				"      amount,food(id,description,category,nutrients\n" + 
				"      [tag='PROCNT' || tag='CHOCDF' || tag='TPCH'](\n" + 
				"        tag,longname,shortname,value,units\n" + 
				"      ))\n" + 
				"    )\n" + 
				"  )\n" + 
				")";
		//@formatter:on
		assertEquals("root(" + "id, title, summary, conclusion, ingGroups(" + "name, ingredients("
				+ "amount, food(id, description, category, nutrients"
				+ "[OR(EQ(tag,PROCNT),OR(EQ(tag,CHOCDF),EQ(tag,TPCH)))]("
				+ "tag, longname, shortname, value, units)))))", parse(dsl).toString());
	}

	public void test4() throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream("root[0.012323]".getBytes());
		DslParser parser = new DslParser(bais);
		try {
			System.out.println(parser.root());
		} catch (Exception e) {
		}
	}

	public void test5() throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream("root[0.01.2323]".getBytes());
		DslParser parser = new DslParser(bais);
		try {
			parser.root();
		} catch (Exception e) {
		}
	}

	public void test6() throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream("root[00.012323]".getBytes());
		DslParser parser = new DslParser(bais);
		try {
			parser.root();
		} catch (Error e) {
		}
	}

	public void test7() throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream("root['0abcd']".getBytes());
		DslParser parser = new DslParser(bais);
		try {
			parser.root();
		} catch (Exception e) {
		}
	}

}
