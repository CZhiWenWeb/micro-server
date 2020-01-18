package com.czw.设计模式.命令模式;

import com.czw.设计模式.命令模式.command.Command;

/**
 * @Author: czw
 * @CreateTime: 2020-01-16 14:16
 * @UpdeteTime: 2020-01-16 14:16
 * @Description:
 */
public class SimpleRemoteControl {
	// 有一个插槽持有命令，而这个命令控制者一个装置
	Command slot;

	public SimpleRemoteControl(){}
	// 用来切换不同的命令，对应改变遥控器的行为
	public void setCommand(Command command){
		slot=command;
	}

	public void buttonWasPressed(){
		slot.execute();
	}

}
