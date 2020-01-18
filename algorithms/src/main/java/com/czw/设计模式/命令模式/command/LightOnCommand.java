package com.czw.设计模式.命令模式.command;

import com.czw.设计模式.命令模式.command.Command;
import com.czw.设计模式.命令模式.model.Ligth;

/**
 * @Author: czw
 * @CreateTime: 2020-01-16 14:10
 * @UpdeteTime: 2020-01-16 14:10
 * @Description:
 */
public class LightOnCommand implements Command {
	Ligth ligth;
	@Override
	public void execute() {
		ligth.on();
	}
	// 构造器传入了某个电灯，然后记录在实例变量中，
	// 一旦调用execute()就由这个电灯对象成为接受者，
	// 负责接受请求
	public LightOnCommand(Ligth ligth){
		this.ligth=ligth;
	}
}
