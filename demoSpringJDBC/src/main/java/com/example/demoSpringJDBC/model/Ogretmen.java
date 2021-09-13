package com.example.demoSpringJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Ogretmen {
	// bu class 'a model diyebiliriz
	// bu class bir POJO 'dur (plain old java object)

	private int id;
	private String name;
}