package vm;

import static vm.ByteCode.*;

public class Program
{
    static Instruction[] program = {
            new Instruction(ICONST, 10),
            new Instruction(IADD, 5),
            new Instruction(GSTORE, 0),
            new Instruction(POP),
            new Instruction(GLOAD, 0),

            new Instruction(CALL, 9),

            new Instruction(IMUL, 3),
            new Instruction(POP),
            new Instruction(HALT),

            //function
            new Instruction(ICONST, 80),
            new Instruction(ISUB, 5),
            new Instruction(GSTORE, 1),
            new Instruction(POP),
            new Instruction(RET)
    };

    public static void main(String[] args)
    {
        VirtualMachine vm = new VirtualMachine(program, 0, 2);
        vm.cpu();
    }
}
