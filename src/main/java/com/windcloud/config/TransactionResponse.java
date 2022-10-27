package com.windcloud.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse<T> {
	
	private T data;
	private Integer status;
	private String message;
	private String tranRefNo;

}
