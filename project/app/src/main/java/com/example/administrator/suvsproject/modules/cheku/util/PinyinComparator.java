package com.example.administrator.suvsproject.modules.cheku.util;

import com.example.administrator.suvsproject.modules.cheku.bean.CarBean;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<CarBean> {

	public int compare(CarBean o1, CarBean o2) {
		if (o1.getFwords().equals("@")
				|| o2.getFwords().equals("#")) {
			return -1;
		} else if (o1.getFwords().equals("#")
				|| o2.getFwords().equals("@")) {
			return 1;
		} else {
			return o1.getFwords().compareTo(o2.getFwords());
		}
	}

}
