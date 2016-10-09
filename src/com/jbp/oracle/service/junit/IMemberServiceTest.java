package com.jbp.oracle.service.junit;


import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jbp.oracle.factory.ServiceFactory;
import com.jbp.oracle.service.IMemberService;
import com.jbp.oracle.service.impl.MemberServiceImpl;
import com.jbp.oracle.vo.Member;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IMemberServiceTest {
	private static String mid ;
	private static String phone;
	static{
		Random ran = new Random();
		mid ="测试ID-" + ran.nextInt(100);
		phone ="测试PHONE -" + ran.nextInt(100);
	}
	@Test
	public void test1Add() {
		try {
			IMemberService im = ServiceFactory.getInstance(MemberServiceImpl.class);
			Member vo = new Member(mid,"测试用户",15,phone,new Date(),"测试数据");
			System.out.println("add"+im.add(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2Edit() {
		try {
			IMemberService im = ServiceFactory.getInstance(MemberServiceImpl.class);
			Member vo = new Member(mid,"测试用户改",15,phone,new Date(),"测试数据改改改改改");
			System.out.println("edit"+im.edit(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test7Remove() {
		try {
			IMemberService im = ServiceFactory.getInstance(MemberServiceImpl.class);
			Set<String> ids = new HashSet<String>();
			ids.add(mid);
			System.out.println("remove"+im.remove(ids));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test3Get() {
		try {
			IMemberService im = ServiceFactory.getInstance(MemberServiceImpl.class);
			System.out.println("get"+ im.get(mid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4List() {
		try {
			IMemberService im = ServiceFactory.getInstance(MemberServiceImpl.class);
			System.out.println("list1 \n"+im.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test5ListIntegerInteger() {
		try {
			IMemberService im = ServiceFactory.getInstance(MemberServiceImpl.class);
			System.out.println("list2 \n"+im.list(1, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test6ListStringStringIntegerInteger() {
		try {
			IMemberService im = ServiceFactory.getInstance(MemberServiceImpl.class);
			Map<String,Object> map = im.list("mid",mid,1, 1);
			System.out.println("list3 "+map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
