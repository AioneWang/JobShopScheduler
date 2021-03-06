package mm_scheduler.instanceScheduler.rules;

import java.io.Serializable;
import java.util.Comparator;

import mm_scheduler.instanceScheduler.instance.domain.basicdata.Operation;
import mm_scheduler.instanceScheduler.rules.basic.OperationRule;

/**
 * LSO规则，选择后继工序加工时间最长的工件
 * 
 * @author hba
 *
 */
public class OperationLSO extends OperationRule implements Comparator<Operation>, Serializable {
	public OperationLSO() {
		super(20, "LSO");
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Operation operTask1, Operation operTask2) {
		if (operTask1.equals(operTask2))
			return 0;
		int i = -1;
		double so1 = operTask1.getSuccOp() == null ? 0 : operTask1.getSuccOp().getWorkTime();
		double so2 = operTask2.getSuccOp() == null ? 0 : operTask2.getSuccOp().getWorkTime();
		try {
			if (so1 < so2)
				i = 1;
			else if (so1 == so2) {
				if (operTask1.getWorkTime() < operTask2.getWorkTime())
					i = 1;
				else if (operTask1.getWorkTime() == operTask2.getWorkTime()) {
					// 交货期
					if (operTask1.getID() > operTask2.getID())
						i = 1;
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
