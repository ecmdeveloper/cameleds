package com.ecmdeveloper.eds.beans;

import java.util.ArrayList;
import java.util.List;

public class ChoiceListFactory {

	public List<String> create(Object object) {
		ArrayList<String> choices = new ArrayList<String>();
		choices.add("John");
		choices.add("Paul");
		choices.add("George");
		choices.add("Ringo");
		return choices;
	}
}
