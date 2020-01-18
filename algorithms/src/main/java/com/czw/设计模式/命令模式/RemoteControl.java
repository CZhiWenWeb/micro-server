package com.czw.设计模式.命令模式;

import com.czw.设计模式.命令模式.command.Command;


/**
 * @Author: czw
 * @CreateTime: 2020-01-16 14:46
 * @UpdeteTime: 2020-01-16 14:46
 * @Description:
 */
public class RemoteControl {
	Command[] onCommands;
	Command[] offCommands;
	public RemoteControl(){
		onCommands=new Command[7];
		offCommands=new Command[7];
	}
	public void setCommand(int slot,Command onCommand,Command offCommand){
		onCommands[slot]=onCommand;
		offCommands[slot]=offCommand;
	}
	public void onButtonWasPushed(int slot){
		onCommands[slot].execute();
	}
	public void offButtonWasPushed(int slot){
		offCommands[slot].execute();
	}
}
