package com.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Utility {

	public static void main(String[] args) {
		
		String[] a = returnArray("A,B,C", ",");
		String[] b = returnArray("A,B,C,D,E", ",");
		System.out.println(compareList(a, b, null));

	}

	public static String[] returnArray(String data, String regex) {
		String[] str = null;
		if (null != data && !"".equalsIgnoreCase(data)) {
			if (null != regex && !"".equalsIgnoreCase(regex)) {
				str = data.split(regex);
			}
		}
		return str;
	}

	public static Map<String, String> compareList(String[] list1, String[] list2, String ext) {

		Map<String, String> map = new HashMap<>();
		Collection<String> listOne = Arrays.asList(list1);

		Collection<String> listTwo = Arrays.asList(list2);

		Collection<String> similar = new HashSet<String>(listOne);
		Collection<String> different = new HashSet<String>();
		different.addAll(listOne);
		different.addAll(listTwo);

		similar.retainAll(listTwo);
		different.removeAll(similar);
		map.put("SIMILAR", similar.toString());
		map.put("DIFFERENT", different.toString());
		return map;
	}

}
