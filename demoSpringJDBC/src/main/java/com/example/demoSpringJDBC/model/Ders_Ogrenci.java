package com.example.demoSpringJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Ders_Ogrenci
{
	private int kayitId;

	private int ogrenciId;

	private int dersId;
}