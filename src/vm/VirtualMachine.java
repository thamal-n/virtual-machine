package vm;

public class VirtualMachine
{
    int[] data;
    int[] stack;
    Instruction[] code;

    int ip;
    int sp = -1;
    int fp = 0;

    public VirtualMachine(Instruction[] code, int main, int dataSize)
    {
        this.code = code;
        this.ip = main;
        data = new int[dataSize];
        stack = new int[100];
    }

    public void cpu()
    {
        loop: while (ip < code.length)
        {
            Instruction instruction = code[ip++];

            instruction.print(ip - 1);

            switch (instruction.getOpcode())
            {
                case IADD:
                    stack[sp] += instruction.getArgs()[0];
                    break;

                case ISUB:
                    stack[sp] -= instruction.getArgs()[0];
                    break;

                case IMUL:
                    stack[sp] *= instruction.getArgs()[0];
                    break;

                case GSTORE:
                    data[instruction.getArgs()[0]] = stack[sp];
                    break;

                case GLOAD:
                    stack[++sp] = data[instruction.getArgs()[0]];
                    break;

                case ICONST:
                    stack[++sp] = instruction.getArgs()[0];
                    break;

                case POP:
                    stack[sp--] = 0;
                    break;

                case PRINT:
                    System.out.println(stack[sp]);
                    break;

                case CALL:
                    stack[++sp] = fp;
                    stack[++sp] = ip;
                    fp = sp;
                    ip = instruction.getArgs()[0];
                    break;

                case RET:
                    sp = fp;
                    ip = stack[sp--];
                    fp = stack[sp--];
                    break;

                case HALT:
                    break loop;
            }

            printStack();
        }

        printData();
    }

    void printStack()
    {
        System.err.print("stack = [ ");

        for (int i = 0; i <= sp; i++)
            System.err.print(stack[i] + " ");

        System.err.println("]");
    }

    void printData()
    {
        System.err.print("\n\ndata = [ ");

        for (int i : data)
            System.err.print(i + " ");

        System.err.println("]");
    }
}
