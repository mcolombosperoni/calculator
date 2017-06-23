import com.mcs.calc.common.CalcApplicationException;
import com.mcs.calc.controller.CalculatorCmdController;
import com.mcs.calc.dto.ResultDto;


public class CommandLineMain {
  public static void main(String[] args){

    final CalculatorCmdController calculatorCmdController = CalculatorCmdController.getInstance();

    ResultDto resultDto = calculatorCmdController.initCalculator();

    do {

      try {

        calculatorCmdController.printAllOperations(resultDto);
        calculatorCmdController.printResult(resultDto);

        int choice = calculatorCmdController.chooseOperation();
        resultDto = calculatorCmdController.doChosenOperation(choice, resultDto);

      } catch (CalcApplicationException e) {
        System.out.println("Error occurred, restore last status");
        calculatorCmdController.cleanScanner();
      }

    } while (!resultDto.isExit());

    calculatorCmdController.printResult(resultDto);
  }

}
