package com.example.demoSpringJDBC.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ogrenci
{
	// bu class 'a model diyebiliriz
	// bu class bir POJO 'dur (plain old java object)
	private int id;

	private int number;

	private String name;

	@Override
	public String toString()
	{
		return id + " - " + number + " - " + name;
	}
}