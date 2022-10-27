package com.windcloud.dto;

import com.windcloud.entity.MessageType;
import com.windcloud.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDTO {

	private String content;
	private User toUser;
	private User fromUser;
	private MessageType msgType;
}
