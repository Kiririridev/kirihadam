package com.kirihadam.kirihadam.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextCountPOJO implements Serializable {
	String text;
	Integer count;

	public TextCountPOJO(String text) {
		this.text = text;
	}
}

