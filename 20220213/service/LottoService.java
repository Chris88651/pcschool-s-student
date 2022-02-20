package com.study.springmvc.case02.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class LottoService {
	private List<Set<Integer>> lottos = new ArrayList<>();
	
	public List<Set<Integer>> getLottos() {
		return lottos;
	}
	
	public void addLotto() {
		lottos.add(0, generateLotto());
	}
	
	public void updateLotto(int index) {
		lottos.set(index, generateLotto());
	}
	
	public void deleteLotto(int index) {
		lottos.remove(index);
	}
	
	private Set<Integer> generateLotto() {
		Random r = new Random();
		// 樂透 539: 1~39 取出不重複的5個號碼
		Set<Integer> lotto = new LinkedHashSet<>();
		while(lotto.size() < 5) {
			lotto.add(r.nextInt(39) + 1);
		}
		return lotto;
	}
	
	//homework
	public void statisticsLotto() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < lottos.size(); i++) {
			//不知道如何將lottos裡的資料轉出
			//list.add(lottos.get(i));
		}
		Map<Integer,Integer> map = new HashMap<>();
		for(int i : list) {
			int count = 1;
			if(map.get(i) != null) {
				count = map.get(i)+1;
			}
			map.put(i,count);
		}
		System.out.print(map.toString());
	}
}
