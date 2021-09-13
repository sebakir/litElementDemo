package com.example.demoSpringJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Ders {

	private int dersId;
	private int ogretmenId;
	private int konuId;
}